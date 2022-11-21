import React, { useEffect, useState } from 'react';
import _ from 'lodash';

/* Echarts */
import * as echarts from 'echarts/core';
import EchartsReact from 'echarts-for-react/lib/core';

const defaultOptions = {
    grid: {
        containLabel: true,
        top: '8%',
        left: '2%',
        right: '10%',
        bottom: '2%'
    },
    toolbox: {
        left: 'left',
        top: 'top',
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            },
            saveAsImage: {}
        }
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            animation: false
        }
    },
    legend: {
        type: 'scroll',
        orient: 'vertical',
        right: 10,
        top: 20,
        bottom: 20,
        textStyle: {
            overflow: 'truncate',
            width: 100
        }
    }
};

const EchartsComponent = (props) => {
    const [options, setOptions] = useState({});

    useEffect(() => {
        const mergeOption = mergeDeep(_.cloneDeep(defaultOptions), props.option);
        setOptions(mergeOption);
    }, [props.option]);

    const mergeDeep = (target, ...sources) => {
        if (!sources.length) return target;
        const source = sources.shift();

        if (isObject(target) && isObject(source)) {
            for (const key in source) {
                if (isObject(source[key])) {
                    if (!target[key]) Object.assign(target, { [key]: {} });
                    mergeDeep(target[key], source[key]);
                } else {
                    Object.assign(target, { [key]: source[key] });
                }
            }
        }

        return mergeDeep(target, ...sources);
    }

    const isObject = (item) => {
        return (item && typeof item === 'object' && !Array.isArray(item));
    }

    return (
        <>
            {
                !_.isEmpty(options) && <EchartsReact echarts={echarts} option={options} style={props.style || { width: "100%", height: "100%" }} onEvents={props.onEvents} />
            }
        </>
    );
}

export default EchartsComponent;