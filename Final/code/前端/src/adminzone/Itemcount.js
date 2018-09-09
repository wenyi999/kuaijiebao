import React, { Component } from 'react';
import './App.css';
import { Table} from 'antd';
import {message} from "antd/lib/index";
import $ from 'jquery';
import {Control} from "react-keeper";

const { Column } = Table;

/*const creditcheckout_list = []

const map = [{
  22:22
},{
  'sam':66
}]

map.map((item,index) => {
    creditcheckout_list.push({
    name: index,
    money: item[index]
  })
})
*/
class paycount extends Component {
  constructor(props) {
    super(props);
    this.state = {
        dataSource: []
    };
    this.loadlist = this.loadlist.bind(this);
    this.checkright = this.checkright.bind(this)
  }
  getCookie= (cname) =>{
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i=0; i<ca.length; i++) {
        let c = ca[i].trim();
        if (c.indexOf(name) === 0) return c.substring(name.length,c.length);
    }
    return "";
  }
  checkright(){
    let name = this.getCookie('username')
    if (name !== 'admin'){
      message.info("您没有权限访问，请使用管理员账号登录")
      Control.go('/login', {name: 'React-Keeper'})
      return false
    }
    return true
  }
  loadlist(){
    $.ajax({
      type : "GET",
      url : "/count",
      data:{
        countStatus:2
      },
      success : function(data) {
        message.info("success")
        this.setState({
            dataSource:JSON.parse(data)
        })
      }.bind(this),
      error : function(data){
        message.info("error")
      }.bind(this)
    }); 
  }
  componentWillMount(){
    this.checkright()?
    this.loadlist():null
  }
  render() {
      const {dataSource} = this.state
    return (
        <Table dataSource={dataSource}
               /*expandedRowRender={record => <p style={{ margin: 0 }}>{record.description}</p>}*/
               pagination={{
                   onChange: (page) => {
                       console.log(page);
                   },
                   pageSize: 8,
               }}>
            <Column
                title="理财商品名称"
                dataIndex="itemname"
                key="itemname"
            />
            <Column
                title="销量统计"
                dataIndex="amount"
                key="amount"
            />



        </Table>
    );


}
}

export default paycount;