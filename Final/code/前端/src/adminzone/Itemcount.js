import React, { Component } from 'react';
import './App.css';
import { Table} from 'antd';
import {message} from "antd/lib/index";
import $ from 'jquery';
import {Control} from "react-keeper";
import echarts from 'echarts/lib/echarts';
import  'echarts/lib/chart/bar';
// 引入提示框和标题组件
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

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
        dataSource: [],
        i_list:[],
        v_list:[],
    };
    this.load = this.load.bind(this);
    this.checkright = this.checkright.bind(this);
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
  load(){
    $.ajax({
      type : "GET",
      url : "/count",
      data : {
        countStatus:2
      },
      success:function(data) {
        this.setState({
            dataSource:JSON.parse(data)
        })
        var item_list = [];
        var value_list=[];
        message.info("success")
        console.log(this.state.dataSource)
        for (var i = 0;i < this.state.dataSource.length;i++){
          item_list.push(this.state.dataSource[i].itemname);
          value_list.push(parseFloat(this.state.dataSource[i].amount));
        }
        console.log(item_list[0]);
        console.log("success1");
        this.setState({
          i_list:item_list,
          v_list:value_list
        })
        console.log(this.state.i_list[0]);
        var myChart = echarts.init(document.getElementById('main'));
        // 绘制图表
        console.log("success3")
        myChart.setOption({
            title: { text: '理财产品销量情况' },
            tooltip: {},
            xAxis: {
                data: this.state.i_list
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: this.state.v_list
            }]
        });
      }.bind(this),
      error : function(data){
        message.info("error")
      }.bind(this)
    }); 
  }

  componentWillMount(){
    if (this.checkright() === true){
       this.load();
    }
  }
  render() {
    return (
      <div id="main" style={{ width: 600, height: 600 }}></div>
    );
  }
}

export default paycount;