import React, { Component } from 'react';
import './App.css';
import {message} from "antd/lib/index";
import $ from 'jquery'
import { Table,Popconfirm,Input,Button } from 'antd';

class cardmanagement extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: '编号',
            dataIndex: 'id',
        }, {
            title: '信用卡号',
            dataIndex: 'credict_number',
        }, {
            title: '方式',
            dataIndex: 'operation',
            render: (text, record) => {
                return (
                    <Popconfirm title="确认解绑吗?" onConfirm={() => this.delete_card(record.credict_number)}>
                        <a href="javascript:;">解绑</a>
                    </Popconfirm>
                );
            },
        }];

        this.state = {
            username: '',
            card:'',
            dataSource: [],

        };
        this.loadlist = this.loadlist.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    getCookie = (cname) => {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf(name) === 0) return c.substring(name.length, c.length);
        }
        return "";
    }

    loadlist() {

        $.ajax({
            type: 'GET',
            url: '/card',
            data: {
                username: this.state.username,
            },
            success: function (data) {
                message.info("success");
                this.setState({
                    dataSource: JSON.parse(data)
                });
            }.bind(this),
            error: function (data) {
                console.log(data)
            }
        })
        console.log(this.state.username)
    }

    delete_card = (credict_number) => {
        //const dataSource = [...this.state.dataSource];
        $.ajax({
            type: 'POST',
            url: '/card',
            data: {
                username: this.state.username,
                credict_number: credict_number,
                card_status: 1
            },
            success: function (data) {
                message.info("success");
                this.setState({
                    dataSource: JSON.parse(data)
                });
            }.bind(this),
            error: function (data) {
                console.log(data)
            }
        })
    }

    change_card  = (e) => {
        console.log("change");
        const { value } = e.target;
        this.setState({card:value});
    }



    handleSubmit = () => {
        console.log("start handleSubmit")
        if (this.state.username===''){message.info("输入不得为空");return;}
        console.log("hey")
        $.ajax({
            type: 'POST',
            url: "/card",
            data: {
                card_status: 0,
                username: this.state.username,
                credict_number: this.state.card
            },
            success: function (data) {

                 if (data === 'duplicatedCard') {
                    message.info("卡号重复");
                    this.setState({
                        sys_inf: data
                    });
                }
                else  {
                    message.info("添加成功！！！");
                     this.setState({
                         dataSource: JSON.parse(data)
                     });
                }


            }.bind(this),

        });
    }


    componentWillMount() {
        let name = this.getCookie('username')
        console.log(name);
        this.setState({username: name}, () => {
            console.log(this.state.data);
            this.loadlist();

        })

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
                <br />
                <Input style = {{width :'50%'}} ref="card" onChange={this.change_card.bind(this)} placeholder="请输入银行卡号" />
                <br /><br />
                <Button type = "primary" onClick={this.handleSubmit.bind(this)}>绑定</Button>
            </div>

        );
    }
}

export default cardmanagement;

