import React, { Component } from 'react';
import './App.css';
import {message} from "antd/lib/index";
import $ from 'jquery'
import { Table } from 'antd';

class debtsmanagement extends Component {
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
            title: '月利率',
            dataIndex: 'rate',
        },{
            title: '还款时间(月）',
            dataIndex: 'repaytime',
        },{
            title: '状态',
            dataIndex: 'status'
        }
    ];

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
            url:'/apply',
            data:{
                creditor_name:'admin',
                applyStatus:2
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

export default debtsmanagement;

