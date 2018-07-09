import React, { Component } from 'react';

import './App.css';

import { Table, Popconfirm } from 'antd';

const debts_list = []
for (let i = 0; i <25; i++) {
    debts_list.push({
        key : i+1,
        creditor_name: 'sam',
        debts:45+i,
        date:"2018-09-09",
        state:false,
        state_message:'未还款'
    })
}

class Loanmanagement extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: '序号',
            dataIndex: 'key',
        }, {
            title: '债权人姓名',
            dataIndex: 'creditor_name',
        },{
            title: '借款金额',
            dataIndex: 'debts',
        },{
            title: '期限',
            dataIndex: 'date',
        },{
            title: '状态',
            dataIndex: 'state_message',
        },{
            title: '方式',
            dataIndex: 'operation',
            render: (text, record) => {
                return (
                    this.state.dataSource.length > 1
                        ? (
                            <Popconfirm title="确认还款吗?" onConfirm={() => this.onRepay(record.key)}>
                                <a href="javascript:;">还款</a>
                            </Popconfirm>
                        ) : null
                );
            },
        }];

        this.state = {
            dataSource: debts_list
        };
    }

    onRepay = (key) => {
        const dataSource = [...this.state.dataSource];
        const target = dataSource.find(item => item.key === key);
        if (target.state === false) {
            target.state = true;
            target.state_message = "已还款"
            this.setState({ dataSource });
        }
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
                        pageSize: 8,
                    }}
                    bordered dataSource={dataSource} columns={columns} />
            </div>
        );
    }
}

export default Loanmanagement;