import React, { useState } from 'react';
import _ from "lodash";
import moment from 'moment';
import Responsive, { WidthProvider } from "react-grid-layout";
import WindowOpener from 'react-window-opener';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGear, faMinus, faCheck } from "@fortawesome/free-solid-svg-icons";

import { Grid, Card, CardHeader, CardContent, Box } from '@mui/material';

const ReactGridLayout = WidthProvider(Responsive);

const staticLayout = {
    0: "TCP 가용성",
    1: "TCP 에러 - Out Of Order",
    2: "TCP 에러 - Lost Segment",
    3: "TCP 에러 - Duplication Ack",
    4: "TCP 에러 - Zero Window",
    5: "TCP 에러 - Retransmission",
    6: "TCP 에러 - Connection Refuse",
    7: "TCP 에러 - Connection Timeout",
    8: "TCP 에러 - Not Responding",
    9: "HTTP 가용성",
    10: "HTTP 응답 - 2xx (성공)",
    11: "HTTP 응답 - 3xx (Redirection)",
    12: "HTTP 응답 - 4xx (단말 에러)",
    13: "HTTP 응답 - 5xx (서버 에러)",
    14: "HTTP 응답 - xxx (Etc...)",
    15: "Stop Transaction",
    16: "Transaction 성능",
    17: "Transaction 응답시간",
    18: "Transaction 응답시간 - Request Data Transfer",
    19: "Transaction 응답시간 - Response Data Transfer",
    20: "Transaction 응답시간 - Data Ack RTT",
    21: "Transaction 응답시간 - Connection RTT",
    22: "단말 응답시간 - Page Design Time",
    23: "단말 응답시간 - Think Time (Gap)",
    24: "서버 시간이 느린 Transaction",
    25: "서버 응답시간 - Initial",
    26: "서버 응답시간 - Application (Buffer Push)",
    27: "서버 응답시간 - Processing",
    28: "Uses",
    29: "Sessions",
    30: "Transactions",
    31: "네트워크 - 사이즈",
    32: "네트워크 - 수",
    33: "네트워크 - 사이즈 (에러)",
    34: "네트워크 - 수 (에러)",
    35: "네트워크 - 사이즈 (Payload)",
    36: "네트워크 - 수 (Payload)",
    37: "네트워크 - 사이즈 (Payload 에러)",
    38: "네트워크 - 수 (Payload 에러)",
};

const OverviewIndex = () => {
    const [layout, setLayout] = useState([]);

    const generateGridDOM = () => {
        return _.map(layout, (obj) => {
            return (
                <Box component="div" key={obj.i}>
                    <Card sx={{ height: "100%" }}>
                        <CardHeader title={(
                            <Grid container className="cursorm">
                                <Grid item sm={11}>{staticLayout[obj.i]}</Grid>
                                <Grid item sm={1} textAlign="right" className="cursorp">
                                    <FontAwesomeIcon icon={faMinus} onClick={() => onRemoveItem(obj.i)} />
                                </Grid>
                            </Grid>
                        )} />
                        <CardContent sx={{ padding: 2 }}>

                        </CardContent>
                    </Card>
                </Box>
            );
        });
    }

    const onRemoveItem = (i) => {
        setLayout(_.reject(layout, { i: i }));
    }

    const onLayoutChange = (layout) => {
        setLayout(layout);
    }

    const onSaveGridLayout = () => {
        console.log(layout)
        console.log(JSON.stringify(layout))
    }

    const bridgeSaveLayoutChange = (e, data) => {
        if (data) {
            const cloneLayout = _.cloneDeep(layout);
            let addLayoutArr = [];
            let xPointer = 0;

            _.forEach(data.saveChk, (obj, i) => {
                if (obj.check) {
                    const filterExistData = _.filter(cloneLayout, (cloneObj) => {
                        return cloneObj.i === obj.key;
                    });

                    const formatWidth = filterExistData.length > 0 ? filterExistData[0].w : 4;

                    if (xPointer + formatWidth > 12) xPointer = 0;

                    const addObj = {
                        i: obj.key,
                        name: obj.name,
                        w: formatWidth,
                        h: filterExistData.length > 0 ? filterExistData[0].h : 8,
                        x: filterExistData.length > 0 ? filterExistData[0].x : xPointer,
                        y: filterExistData.length > 0 ? filterExistData[0].y : Infinity,
                        minW: 4,
                        minH: 8
                    };

                    addLayoutArr.push(addObj);

                    if (filterExistData.length === 0) xPointer += formatWidth;
                }
            });

            setLayout(addLayoutArr);
        }
    }

    const onCreateRandomTimeSeries = (dataRange) => {
        return _.map(_.range(30), (i) => {
            let pushData = [moment().subtract(i, "seconds").format("YYYY-MM-DD HH:mm:ss")];

            _.forEach(_.range(dataRange), () => {
                pushData.push(_.random(0, 100));
            });

            return pushData;
        });
    }

    return (
        <>
            <Grid container>
                <Grid item sm={12}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    Transaction Count
                                    <WindowOpener className="inline-block" url="/popup/dashboard/setWidget" width="1200" height="500" bridge={bridgeSaveLayoutChange}>
                                        <Box ml={1} sx={{ cursor: "pointer" }}><FontAwesomeIcon icon={faGear} /></Box>
                                    </WindowOpener>
                                </Grid>
                                <Grid item sm={2} textAlign="right">
                                    <Box component="span" className="cursorp" onClick={onSaveGridLayout}><FontAwesomeIcon icon={faCheck} /> 저장</Box>
                                    <Box component="span" ml={1} className="day-icon"></Box>
                                </Grid>
                            </Grid>
                        )} />
                    </Card>
                </Grid>
            </Grid>

            <Grid container spacing={1} mt={0}>
                <Grid item sm={2}>
                    <Card>
                        <CardHeader title={(<>
                            Country
                            <Box component="span" sx={{ float: "right" }}>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/refresh--v1.png" style={{ width: '.9rem', marginRight: '.2rem' }} alt="img" /></a>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/external-link.png" style={{ width: '1rem' }} alt="img" /></a>
                            </Box>
                        </>)} />
                        <CardContent>
                            <Grid container sx={{ borderBottom: "1px solid #bbbbbb" }}>
                                <Grid item sm={8}></Grid>
                                <Grid item sm={4} textAlign="right"><b>Transaction</b></Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}><img src="https://img.icons8.com/color/48/000000/globe.png" style={{ width: '.8rem', marginRight: '.2rem' }} alt="img" />ETC</Grid>
                                <Grid item sm={4} textAlign="right">1,050,73M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}><img src="https://img.icons8.com/color/48/000000/south-korea-circular.png" style={{ width: '.8rem', marginRight: '.2rem' }} alt="img" />Korea, Republic of</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}><img src="https://img.icons8.com/color/48/000000/china-circular.png" style={{ width: '.8rem', marginRight: '.2rem' }} alt="img" />China</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}><img src="https://img.icons8.com/color/48/000000/canada-circular.png" style={{ width: '.8rem', marginRight: '.2rem' }} alt="img" />Canada</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}><img src="https://img.icons8.com/color/48/000000/brazil-circular.png" style={{ width: '.8rem', marginRight: '.2rem' }} alt="img" />Brazil</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}><img src="https://img.icons8.com/color/48/000000/japan-circular.png" style={{ width: '.8rem', marginRight: '.2rem' }} alt="img" />Japan</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item sm={2}>
                    <Card>
                        <CardHeader title={(<>
                            Region
                            <Box component="span" sx={{ float: "right" }}>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/refresh--v1.png" style={{ width: '.9rem', marginRight: '.2rem' }} alt="img" /></a>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/external-link.png" style={{ width: '1rem' }} alt="img" /></a>
                            </Box>
                        </>)} />
                        <CardContent>
                            <Grid container sx={{ borderBottom: "1px solid #bbbbbb" }}>
                                <Grid item sm={8}></Grid>
                                <Grid item sm={4} textAlign="right"><b>Transaction</b></Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>서울시 강남구</Grid>
                                <Grid item sm={4} textAlign="right">1,050,73M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>부산광역시</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>경기도 고양시</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>서울특별시</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>대구광역시</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>경기도 여주시 현암동</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item sm={2}>
                    <Card>
                        <CardHeader title={(<>
                            ISP
                            <Box component="span" sx={{ float: "right" }}>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/refresh--v1.png" style={{ width: '.9rem', marginRight: '.2rem' }} alt="img" /></a>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/external-link.png" style={{ width: '1rem' }} alt="img" /></a>
                            </Box>
                        </>)} />
                        <CardContent>
                            <Grid container sx={{ borderBottom: "1px solid #bbbbbb" }}>
                                <Grid item sm={8}></Grid>
                                <Grid item sm={4} textAlign="right"><b>Transaction</b></Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>SK 브로드밴드</Grid>
                                <Grid item sm={4} textAlign="right">1,050,73M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>(주)엘지유플러스</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>(주)아프리카티비</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>더존비즈온</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>(주)가비아</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>두루안</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item sm={2}>
                    <Card>
                        <CardHeader title={(<>
                            OS
                            <Box component="span" sx={{ float: "right" }}>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/refresh--v1.png" style={{ width: '.9rem', marginRight: '.2rem' }} alt="img" /></a>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/external-link.png" style={{ width: '1rem' }} alt="img" /></a>
                            </Box>
                        </>)} />
                        <CardContent>
                            <Grid container sx={{ borderBottom: "1px solid #bbbbbb" }}>
                                <Grid item sm={8}></Grid>
                                <Grid item sm={4} textAlign="right"><b>Transaction</b></Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Win 10</Grid>
                                <Grid item sm={4} textAlign="right">1,050,73M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Win 7</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Andriod</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Win 8.1</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>iOS 13</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>MacOSX 10</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item sm={2}>
                    <Card>
                        <CardHeader title={(<>
                            Browser
                            <Box component="span" sx={{ float: "right" }}>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/refresh--v1.png" style={{ width: '.9rem', marginRight: '.2rem' }} alt="img" /></a>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/external-link.png" style={{ width: '1rem' }} alt="img" /></a>
                            </Box>
                        </>)} />
                        <CardContent>
                            <Grid container sx={{ borderBottom: "1px solid #bbbbbb" }}>
                                <Grid item sm={8}></Grid>
                                <Grid item sm={4} textAlign="right"><b>Transaction</b></Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>MSIE 11</Grid>
                                <Grid item sm={4} textAlign="right">1,050,73M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Android</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Safari</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Chrome 49</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Chrome 64</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Chrome 66</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item sm={2}>
                    <Card>
                        <CardHeader title={(<>
                            Mobile
                            <Box component="span" sx={{ float: "right" }}>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/refresh--v1.png" style={{ width: '.9rem', marginRight: '.2rem' }} alt="img" /></a>
                                <a className="cursorp" href="{() => false}"><img src="https://img.icons8.com/ios-glyphs/30/000000/external-link.png" style={{ width: '1rem' }} alt="img" /></a>
                            </Box>
                        </>)} />
                        <CardContent>
                            <Grid container sx={{ borderBottom: "1px solid #bbbbbb" }}>
                                <Grid item sm={8}></Grid>
                                <Grid item sm={4} textAlign="right"><b>Transaction</b></Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Samsung</Grid>
                                <Grid item sm={4} textAlign="right">1,050,73M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>iPhone</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>iPhone Tablet</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>LG</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Mobile Safari</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                            <Grid container>
                                <Grid item sm={8}>Facebook</Grid>
                                <Grid item sm={4} textAlign="right">785.56M</Grid>
                            </Grid>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>

            <Box component="div" className="autoy" mt={1} sx={{ height: "70% !important" }}>
                <ReactGridLayout
                    layout={layout}
                    className="layout"
                    cols={12}
                    rowHeight={30}
                    margin={[5, 5]}
                    onLayoutChange={onLayoutChange}
                >
                    {generateGridDOM()}
                </ReactGridLayout>
            </Box>
        </>
    );
};

export default OverviewIndex;