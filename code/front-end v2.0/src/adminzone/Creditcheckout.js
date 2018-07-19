import React, { Component } from 'react';
import './App.css';

import { Table} from 'antd';

const { Column } = Table;

const creditcheckout_list = []

for (let i = 0; i <5; i++) {
    creditcheckout_list.push({
        key : "张三",
        product_name: "本科",
        money:"上海交通大学",
        rate: "2000",
        date:"1500"
    })
}
class creditcheckout extends Component {

    render() {

        return (
            <Table dataSource={creditcheckout_list}>
                <Column
                    title="申请人"
                    dataIndex="key"
                    key="key"
                />
                <Column
                    title="学历"
                    dataIndex="product_name"
                    key="product_name"
                />
                <Column
                    title="学校/工作单位"
                    dataIndex="money"
                    key="money"
                />
                <Column
                    title="月收入"
                    dataIndex="rate"
                    key="rate"
                />
                <Column
                    title="信用额度"
                    dataIndex="date"
                    key="date"
                />
                <Column
                    title="审核"
                    key="action"
                />
            </Table>
        );
    }
}

export default creditcheckout;







