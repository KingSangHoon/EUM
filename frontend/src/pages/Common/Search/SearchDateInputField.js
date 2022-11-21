import React from 'react';
import { OutlinedInput, InputAdornment, FormControl } from '@mui/material';

import { CalendarTwoTone } from '@ant-design/icons';

const DateInputField = React.forwardRef((props, ref) => {
    return (
        <FormControl fullWidth>
            <OutlinedInput
                size="small"
                readOnly={true}
                disabled={props.disabled}
                onClick={props.onClick}
                value={props.value}
                endAdornment={
                    <InputAdornment position="end">
                        <CalendarTwoTone />
                    </InputAdornment>
                }
            />
        </FormControl>
    )
})

export default DateInputField;