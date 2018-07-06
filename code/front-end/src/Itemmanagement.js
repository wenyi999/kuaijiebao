import React, { Component } from 'react';

import './App.css';

import { Table} from 'antd';

const { Column } = Table;

const debts_list = []
for (let i = 0; i <25; i++) {
  debts_list.push({
    key : i+1,
    product_name: '债券No.'+ (i+1),
    money:45+i,
    rate: "1%",
    date:"2018-07-09 ~ 2018-09-09"
  })
}

class Itemmanagement extends Component {
  render(){
    return (
      <Table dataSource={debts_list}>
        <Column
          title="购买单号"
          dataIndex="key"
          key="key"
        />
        <Column
          title="产品名称"
          dataIndex="product_name"
          key="product_name"
        />
        <Column
          title="投资金额"
          dataIndex="money"
          key="money"
        />
        <Column
          title="利率"
          dataIndex="rate"
          key="rate"
        />
        <Column
          title="期限"
          dataIndex="date"
          key="date"
        />
        <Column
          title="兑现"
          key="action"
        />
      </Table>
    )
  }
}

export default Itemmanagement;
