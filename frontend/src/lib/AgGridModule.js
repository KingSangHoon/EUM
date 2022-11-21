import React from 'react';
import PropTypes from 'prop-types';
import { gridHeight } from './common';
import { AgGridReact } from 'ag-grid-react';

const AgGridModule = (props) => (
    <AgGridReact
        headerHeight={gridHeight}
        floatingFiltersHeight={gridHeight}
        getRowHeight={params => params.node.id.indexOf('detail') < 0 ? props.rowHeight : 300}
        rowSelection={props.rowSelection}
        groupSelectsChildren={props.groupSelectsChildren}
        groupSelectsFiltered={true}
        suppressScrollOnNewData={true}
        rowMultiSelectWithClick={props.rowMultiSelectWithClick}
        /* onCellFocused={event => {
            if (event.column) {
                if (event.column.colDef.cellClass.indexOf("icon-window") !== -1) {
                    event.api.gridOptionsWrapper.gridOptions.suppressRowClickSelection = true;
                } else {
                    event.api.gridOptionsWrapper.gridOptions.suppressRowClickSelection = false;
                }
            }
        }} */
        columnDefs={props.columnDefs}   // 필수
        rowData={props.rowData}         // 필수
        onGridReady={params => props.onGridReady(params, props.gridName)}   // 필수
        onRowGroupOpened={params => props.handleResize(params)}
        getNodeChildDetails={data => props.formatGroupChild(data)}
        onModelUpdated={params => props.handleResize(params)}
        onCellMouseDown={params => props.onCellMouseDown(params)}
        onCellMouseOver={event => props.onCellMouseOver(event)}
        onCellClicked={event => props.onCellClicked(event)}
        onRowSelected={event => props.onRowSelected(event)}
        onRowDataChanged={props.onRowDataChanged}
        onDragStopped={props.onDragStopped}
        onCellEditingStopped={props.onCellEditingStopped}
        onSelectionChanged={props.onSelectionChanged}
        onBodyScroll={props.onBodyScroll}
        isRowSelectable={props.isRowSelectable}
        singleClickEdit={true}
        //sideBar={props.sideBar}
        //masterDetail={props.masterDetail}
        //detailCellRendererParams={props.detailCellRendererParams}
        defaultColDef={{
            sortable: true,
            resizable: true,
            cellClass: "cell-wrap-text ag-grid-cell",
            autoHeight: true,
            floatingFilter: true,
            filterParams: { newRowsAction: "keep" }
        }}>
    </AgGridReact>
);

AgGridModule.propTypes = {
    rowData: PropTypes.array,
    columnDefs: PropTypes.array,
    gridName: PropTypes.string,
    rowHeight: PropTypes.number,
    rowSelection: PropTypes.string,
    rowMultiSelectWithClick: PropTypes.bool,
    groupSelectsChildren: PropTypes.bool,
    //masterDetail: PropTypes.string,
    onGridReady: PropTypes.func,
    handleResize: PropTypes.func,
    onRowDataChanged: PropTypes.func,
    formatGroupChild: PropTypes.func,
    onCellMouseDown: PropTypes.func,
    onCellMouseOver: PropTypes.func,
    onCellClicked: PropTypes.func,
    onRowSelected: PropTypes.func,
    onDragStopped: PropTypes.func,
    onCellEditingStopped: PropTypes.func,
    onSelectionChanged: PropTypes.func,
    onBodyScroll: PropTypes.func,
    isRowSelectable: PropTypes.func,
    //detailCellRendererParams: PropTypes.func,
    //sideBar: PropTypes.string,
};
AgGridModule.defaultProps = {
    rowData: [],
    columnDefs: [],
    gridName: "",
    rowHeight: gridHeight,
    rowSelection: "multiple",
    rowMultiSelectWithClick: true,
    groupSelectsChildren: true,
    //masterDetail: "false",
    onGridReady: () => { },
    handleResize: () => { },
    onRowDataChanged: () => { },
    formatGroupChild: () => { },
    onCellMouseDown: () => { },
    onCellMouseOver: () => { },
    onCellClicked: () => { },
    onRowSelected: () => { },
    onDragStopped: () => { },
    onCellEditingStopped: () => { },
    onSelectionChanged: () => { },
    onBodyScroll: () => { },
    //detailCellRendererParams: () => { },
    //sideBar: "false"
};

export { AgGridModule };