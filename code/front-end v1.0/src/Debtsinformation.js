import React, { Component } from 'react';

import './App.css';

import { Table, Popconfirm } from 'antd';

const debts_list = []
for (let i = 0; i <25; i++) {
    debts_list.push({
        key : i+1,
        debtor_name: 'sam',
        debts:45+i,
        date:2,
        interest:1,
        reason:"买房",
        state:false,
        state_message:'未还款'
    })
}

class Debtsinformation extends Component {
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
            title: '借款期限(月）',
            dataIndex: 'date',
        },{
            title: '月利率（%）',
            dataIndex: 'interest',
        },{
            title: '借款目的',
            dataIndex: 'reason',
        },{
            title: '方式',
            dataIndex: 'operation',
            render: (text, record) => {
                return (
                    this.state.dataSource.length > 1
                        ? (
                            <Popconfirm title="确认出借吗?" onConfirm={() => this.onRepay(record.key)}>
                                <a href="javascript:;">出借</a>
                            </Popconfirm>
                        ) : null
                );
            },}

        ];

        this.state = {
            dataSource: debts_list
        };
    }
    onRepay = (key) => {
        const dataSource = [...this.state.dataSource];
        this.setState({ dataSource: dataSource.filter(item => item.key !== key) });
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

export default Debtsinformation;
