import React, { Component } from 'react';

import './App.css';

import { Table, Popconfirm } from 'antd';


import $ from 'jquery'
import {message} from "antd/lib/index";


class Debtsinformation extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: '借款单号',
            dataIndex: 'a_id',
        }, {
            title: '债务人姓名',
            dataIndex: 'username',
        },{
            title: '借款金额',
            dataIndex: 'amount',
        },{
            title: '借款期限(月）',
            dataIndex: 'repaytime',
        },{
            title: '月利率（%）',
            dataIndex: 'rate',
        },{
            title: '方式',
            dataIndex: 'operation',
            render: (text, record) => {
                return (record.username  !=='admin'
                    ?(


                            <Popconfirm title="确认出借吗?" onConfirm={() => this.onLend(record.a_id)}>
                                <a href="javascript:;">出借</a>
                            </Popconfirm>
                        ): null
                );
            },}

        ];

        this.state = {
            username:"",
            dataSource: []
        };
        this.loadlist = this.loadlist.bind(this);
    }
    loadlist(){
        //const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'GET',
            url:'/apply',
            data:{
                username:'admin',
                applyStatus: 0
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
    onLend = (a_id) => {
        //const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'POST',
            url:'/apply',
            data:{
                username:'admin',
                a_id: a_id,
                applyStatus: 4
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

    componentWillMount(){

        this.loadlist();
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
                        pageSize: 8
                    }}
                    bordered dataSource={dataSource} columns={columns} />
            </div>
        );
    }
}

export default Debtsinformation;
