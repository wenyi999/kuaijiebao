import React, { Component } from 'react';
import './App.css';
import $ from 'jquery';
import {message} from 'antd/lib/index'
import { Table, Popconfirm } from 'antd';


class Itemmanagement extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: '产品名称',
            dataIndex: 'item_name',
        },{
            title: '投资金额',
            dataIndex: 'amount',
        },{
            title: '利率',
            dataIndex: 'item_rate',
        },/*{
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
        }*/];

        this.state = {
            username:"",
            dataSource: []
        };
        this.loadlist = this.loadlist.bind(this);
    }

    loadlist(){
        const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'GET',
            url:'/buy',
            data:{
                username:this.state.username,
            },
            success:function(data){
                message.info("success");
                this.setState({
                    dataSource:JSON.parse(data)
                });
            }.bind(this),
            error:function(data){
                console.log(data)
            }
        })
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

    componentWillMount(){
        let name = this.getCookie('username')
        console.log(name);
        this.setState({username:name},() => {
            console.log(this.state.data);
            this.loadlist();

        })

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