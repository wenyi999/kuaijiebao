import React, { Component } from 'react';

import './App.css';

import { Table, Popconfirm } from 'antd';

const item_list = []
for (let i = 0; i <25; i++) {
    item_list.push({
        key : i+1,
        product_name: '债券No.'+ (i+1),
        money:45+i,
        rate: "1%",
        amount: 1,
        date:"2018-07-09 ~ 2018-09-09"
    })
}

class Itemmanagement extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: '购买单号',
            dataIndex: 'key',
        }, {
            title: '产品名称',
            dataIndex: 'product_name',
        },{
            title: '投资金额',
            dataIndex: 'money',
        },{
            title: '利率',
            dataIndex: 'rate',
        },{
            title: '期限',
            dataIndex: 'date',
        },{
            title: '数量',
            dataIndex: 'amount',
        },{
            title: '方式',
            dataIndex: 'operation',
            render: (text, record) => {
                return (
                    this.state.dataSource.length > 1
                        ? (
                            <Popconfirm title="确认兑现吗?" onConfirm={() => this.onRepay(record.key)}>
                                <a href="javascript:;">兑现</a>
                            </Popconfirm>
                        ) : null
                );
            },
        }];

        this.state = {
            dataSource: item_list
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
                        pageSize: 8,
                    }}
                    bordered dataSource={dataSource} columns={columns} />
            </div>
        );
    }
}

export default Itemmanagement;