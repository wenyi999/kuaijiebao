import React, { Component } from 'react';

import './App.css';

import { List, Card,Button } from 'antd';

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

class Itemlist extends Component {

  render(){
    return (
      <List
        pagination={{
          onChange: (page) => {
            console.log(page);
          },
          pageSize: 3,
        }}
        grid={{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 6, xxl: 3 }}
        dataSource={debts_list}
        renderItem={item => (
          <List.Item>
            <Card title={item.product_name}>
            购买金额: {item.money}<br />
            利率: {item.rate}<br />
            期限: {item.date}<br />
            <Button type = "primary">购买</Button>
            </Card>
          </List.Item>
        )}
      />
    )
  }
}

export default Itemlist;