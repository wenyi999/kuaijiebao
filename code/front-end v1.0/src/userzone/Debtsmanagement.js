import React, { Component } from 'react';

import './App.css';

import { Table, Popconfirm } from 'antd';

const debts_list = []
for (let i = 0; i <25; i++) {
    debts_list.push({
        key : i+1,
        debtor_name: 'sam',
        debts:45+i,
        date:"2018-09-09",
        state:false,
        state_message:'未还款'
    })
}

class debtsmanagement extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: '序号',
            dataIndex: 'key',
        }, {
            title: '债务人姓名',
            dataIndex: 'debtor_name',
        },{
            title: '借款金额',
            dataIndex: 'debts',
        },{
            title: '期限',
            dataIndex: 'date',
        },{
            title: '状态',
            dataIndex: 'state_message',
        }];

        this.state = {
            dataSource: debts_list
        };
    }


    render() {
        const { dataSource } = this.state;
        const columns = this.columns;
        return (
            <div>
                <Table
                    pagination={{
                        onChange: (page) => {
                            console.log(page);
                        },
                        pageSize: 8
                    }}
                    bordered dataSource={dataSource} columns={columns} />
            </div>
        );
    }
}

export default debtsmanagement;

