
import React, {Component} from 'react';
import {message} from "antd/lib/index";
import $ from 'jquery'




import './App.css';

import { Card,Radio,Input,Button } from 'antd';

const RadioGroup = Radio.Group;


class Userinformation extends Component {
    constructor(props) {
        super(props);
        this.user = {
            username:'admin',
            id: 123456789123456789,
            phone: 11111111111,
            credit_level: '100',
            credit_limit: 10000,
            card: [
                1111111111111111111,2222222222222222222
            ]
        }
        this.state = {
            username:"",
            dataSource: []
        };
        this.loadlist = this.loadlist.bind(this);
    }
    onChange = (e) => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    }
    loadlist(){
        const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'GET',
            url:'/user',
            data:{
                username:'admin',
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
        return (
            <div style={{padding:"50px"}}>

                <Card
                    style={{ width: '50%' }}
                    title="用户名"
                >
                    <Input value={this.user.username} />
                </Card>

                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="身份证"
                >
                    <Input defaultValue={this.user.id} />
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="手机号码"
                >
                    <Input defaultValue={this.user.phone} />
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="信用"
                >
                    信用评级：{this.user.credit_level}
                    <br />
                    信用额度：{this.user.credit_limit}
                    <br />
                    <Button type = "primary">申请额度</Button>
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="银行卡"
                >
                    {
                        this.user.card.map(function(card_id){
                            return <div>
                                <li key = {card_id}>{card_id}</li>

                                <Button>解绑</Button>
                            </div>;
                        })
                    }
                    <br />
                    <Input style = {{width :'50%'}}placeholder="请输入银行卡号" />
                    <br /><br />
                    <Button type = "primary">绑定</Button>
                </Card>
                <br /><br />
                <Button type = "primary" style={{marginLeft:"20%"}}>保存</Button>
            </div>
        );
    }
}

export default Userinformation;
