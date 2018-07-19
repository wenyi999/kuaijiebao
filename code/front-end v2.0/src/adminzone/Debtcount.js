import React, { Component } from 'react';
import './App.css';
import { Table} from 'antd';

const { Column } = Table;

const creditcheckout_list = []

for (let i = 0; i <5; i++) {
    creditcheckout_list.push({
        key : "张三",
        product_name: "5000",
        money:"8",
        rate: "2000",
        date:"1500"
    })
}


class debtscount extends Component {

    render() {
        return (
            <Table dataSource={creditcheckout_list}
                   expandedRowRender={record => <p style={{ margin: 0 }}>{record.description}</p>}
            >
                <Column
                    title="用户名"
                    dataIndex="key"
                    key="key"
                />
                <Column
                    title="借款总金额"
                    dataIndex="product_name"
                    key="product_name"
                />



            </Table>
        );


    }
}

export default debtscount;

