import React, { Component } from 'react';

import './App.css';

import $ from 'jquery'

import { Table, Popconfirm } from 'antd';
import {message} from "antd/lib/index";



class Loanmanagement extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: '借款单号',
            dataIndex: 'a_id',
        }, {
            title: '债权人姓名',
            dataIndex: 'creditor_name',
        },{
            title: '借款金额',
            dataIndex: 'amount',
        },{
            title: '利率',
            dataIndex: 'rate',
        },{
            title: '还款时间(月）',
            dataIndex: 'repaytime',
        },{
            title: '状态',
            dataIndex: 'status',
        },{
            title: '方式',
            dataIndex: 'operation',
            render: (text, record) => {
                return (record.status==='Lent'
                        ?(
                        <Popconfirm title="确认还款吗?" onConfirm={() => this.onRepay(record.a_id)}>
                        <a href="javascript:;">还款</a>
                    </Popconfirm>) : null
                );
            },
        }];

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
                applyStatus: 1
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
    onRepay = (a_id) => {
        //const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'POST',
            url:'/apply',
            data:{
                username:'admin',
                a_id: a_id,
                applyStatus: 3
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
                        pageSize: 8,
                    }}
                    bordered dataSource={dataSource} columns={columns} />
            </div>
        );
    }
}

export default Loanmanagement;