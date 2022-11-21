import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { Card, CardHeader, CardContent, Box } from '@mui/material';
import Chart from 'react-apexcharts';

import { hiddenComponentPopup } from '../../../lib/common';

const PopupCommonChartLatency = () => {
    const [latencyChartOption] = useState({
        grid: {
            row: {
                colors: ['#f3f4f5', '#fff'],
                opacity: 1
            }
        },
        plotOptions: {
            bar: {
                horizontal: true,
                barHeight: "50%",
                rangeBarGroupRows: true
            }
        },
        fill: {
            type: "solid"
        },
        xaxis: {
            labels: {
                formatter: (value) => {
                    return value + " ms";
                }
            }
        },
        yaxis: {
            labels: {
                style: {
                    whiteSpace: "nowrap",
                    overflow: "hidden",
                    textOverflow: "ellipsis",
                    width: "400px",
                    fontWeight: 400,
                    fontFamily: "FontAwesome, Arial",
                },
                formatter: (value) => {
                    if (_.includes(value, "Empty Name")) {
                        return "";
                    } else {
                        const splitIdx = _.split(value, "|idx=");
                        const splitExp = _.split(splitIdx[0], " ");
                        return splitExp[1];
                    }
                }
            }
        },
        legend: {
            position: "top"
        },
        tooltip: {
            custom: ({ seriesIndex, dataPointIndex, w }) => {
                const target = w.globals.initialSeries[seriesIndex];
                const data = target.data[dataPointIndex];
                const resultData = parseFloat((data.y[1] - data.y[0]).toFixed(6));
                let category = "";

                if (_.includes(data.x, "")) {
                    const splitExp = data.x.split(" ");
                    const splitIdx = splitExp[1].split("|idx=");
                    category = splitIdx[0];
                }

                return `<div class="apexcharts-tooltip-rangebar"><span style="color: ${target.color}">● </span><b>${target.name}:</b><div>
                        <span class="category">${category}: </span>
                        <span class="value">${resultData}</span></div></div>`;
            }
        }
    });
    const [latencyChartSeries, setLatencyChartSeries] = useState([]);

    useEffect(() => {
        getFindoneStateData();

        hiddenComponentPopup();
        window.addEventListener("resize", hiddenComponentPopup);

        return () => {
            window.removeEventListener('resize', hiddenComponentPopup);
        }
    }, []);

    const getFindoneStateData = () => {
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);
                setLatencyChartSeries(jsonParam);
            }
        }, 100);
    }

    return (
        <Card>
            <CardHeader title="Latency Chart"></CardHeader>
            <CardContent>
                <Box sx={{ overflowY: "auto", height: "810px" }}>
                    {latencyChartSeries.length > 0 &&
                        <Chart options={latencyChartOption} series={latencyChartSeries} type="rangeBar" width="98%" height={latencyChartSeries[0].data.length > 50 ? 20 * latencyChartSeries[0].data.length : "97%"} />}
                </Box>
            </CardContent>
        </Card>
    );
};

export default PopupCommonChartLatency;