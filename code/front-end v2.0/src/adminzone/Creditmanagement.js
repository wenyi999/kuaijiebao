import React, { Component } from 'react';
import './App.css';
import { Table} from 'antd';

const { Column } = Table;

const creditcheckout_list = []

for (let i = 0; i <5; i++) {
    creditcheckout_list.push({
        key : "张三",
        product_name: "1500",
        money:"8",
        rate: "2000",
        date:"1500"
    })
}

class creditmanagement extends Component {

    render() {

        return (
            <Table dataSource={creditcheckout_list}>
                <Column
                    title="用户名"
                    dataIndex="key"
                    key="key"
                />
                <Column
                    title="信用额度"
                    dataIndex="product_name"
                    key="product_name"
                />
                <Column
                    title="信用评分"
                    dataIndex="money"
                    key="money"
                />

                <Column
                    title="修改"
                    key="action"
                />
            </Table>
        );
    }
}

export default creditmanagement;

