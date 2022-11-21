import React, { Component } from 'react';
import Highcharts from "highcharts/highstock";
import _ from 'lodash';

Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

const defaultOptions = {
    chart: {
        height: 250,
        zoomType: 'xy'
    },
    title: {
        text: null,
        style: {
            fontSize: "15px"
        }
    },
    credits: {
        enabled: false
    },
    legend: {
        layout: 'horizontal',
        maxHeight: 40,
        itemStyle: {
            color: '#000000',
            fontWeight: 'bold',
            textOverflow: "ellipsis",
            width: 130
        },
        borderWidth: 0,
        backgroundColor: Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF'
    },
    xAxis: {
        type: 'datetime',
        labels: {
            format: "{value:%m-%d<br/>%H:%M}",
            autoRotation: false,
            style: {
                textOverflow: 'ellipsis',
                whiteSpace: 'nowrap',
                width: '200px',
                'min-width': '200px'
            }
        },
        crosshair: true
    },
    yAxis: {
        title: {
            text: null
        },
        min: 0,
        minRange: 0.1,
        labels: {
            align: 'left',
            x: 0,
            y: -2,
            useHTML: true,
            formatter: function () {
                const formatUnit = this.axis.options.labels.format ? " " + this.axis.options.labels.format.split(" ")[1] : "";
                return '<span class="hc-label">' + this.value + formatUnit + '</span>';
            }
        }
    },
    plotOptions: {
        series: {
            turboThreshold: 0,
        },
        area: {
            fillOpacity: 0.5,
            marker: {
                enabled: false,
                radius: 0
            },
            lineWidth: 1
        },
        line: {
            marker: {
                radius: 0
            },
            lineWidth: 1
        },
        spline: {
            marker: {
                radius: 0
            },
            lineWidth: 1
        },
        scatter: {
            marker: {
                symbol: 'circle',
                radius: 2
            },
            tooltip: {
                pointFormat: '{point.x: %Y-%m-%d %H:%M:%S}: <b>{point.y}</b>'
            }
        },
        pie: {
            style: {
                textOverflow: 'clip'
            },
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                style: {
                    fontWeight: '500'
                }
            }
        }
    },
    tooltip: {
        shared: true,
        xDateFormat: '%Y-%m-%d %H:%M:%S',
        outside: true,
        style: {
            zIndex: 10000
        }
    },
    series: [{
        data: [1, 2, 3, 4, 5, 6]
    }]
};

class ChartComponent extends Component {

    componentDidMount() {
        const options = Highcharts.merge(defaultOptions, this.props.options);
        this.chart = Highcharts.chart(this.container, options);

        // 차트 동시간대 이벤트
        if (this.props.syncId) {
            ['mousemove', 'touchmove', 'touchstart'].forEach((eventType) => {
                const container = document.getElementById(this.props.syncId);
                container.addEventListener(eventType, this.syncChartEvt);
            });
        }
    }

    componentWillUnmount() {
        this.chart.destroy();

        if (this.props.syncId) {
            ['mousemove', 'touchmove', 'touchstart'].forEach((eventType) => {
                const container = document.getElementById(this.props.syncId);
                container.removeEventListener(eventType, this.syncChartEvt);
            });
        }
    }

    componentDidUpdate(prevProps) {
        if (this.props.options !== prevProps.options) {
            this.chart.update(this.props.options);
        }
    }

    syncChartEvt = (e) => {
        if (this.chart.userOptions.chart.isSynced) {
            let event = this.chart.pointer.normalize(e);
            let points = [];

            _.forEach(this.chart.series, (obj) => {
                if (obj.searchPoint(event, true)) points.push(obj.searchPoint(event, true));
            });

            if (points.length > 0) {
                this.chart.tooltip.refresh(points);

                _.forEach(points, (obj) => {
                    obj.onMouseOver();
                });
            }
        }

        Highcharts.Pointer.prototype.reset = function () { };
    }

    render() {
        return (
            <div ref={container => this.container = container}></div>
        );
    }
}

export default ChartComponent;