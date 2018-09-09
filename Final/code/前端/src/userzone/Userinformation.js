
import React, {Component} from 'react';
import {message} from "antd/lib/index";
import $ from 'jquery'




import './App.css';

import { Card,Radio,Input,Button } from 'antd';

const RadioGroup = Radio.Group;
/*


                <Card
                    style={{ width: '50%' }}
                    title="银行卡"
                >

                    {this.state.dataSource2.credict_number}

                    <br />
                    <Input style = {{width :'50%'}}placeholder="请输入银行卡号" />
                    <br /><br />
                    <Button type = "primary">绑定</Button>
                </Card>



*/
class Userinformation extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username:"",
            dataSource: [],


        };
        this.loaduserinformation = this.loaduserinformation.bind(this);


    }
    onChange = (e) => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    }
    loaduserinformation(){
      // const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'GET',
            url:'/user',
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
            console.log(this.state.username);
            this.loaduserinformation();

       } )


    }
    render() {

        return (

            <div style={{padding:"50px"}}>


                <Card
                    style={{ width: '50%' }}
                    title="用户名"
                >
                    {this.state.username}
                </Card>

                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="身份证"
                >
                    {this.state.dataSource.id}
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="手机号码"
                >
                    {this.state.dataSource.phone}
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="信用"
                >
                    信用评分：{this.state.dataSource.credit_level}
                    <br />
                    信用额度：{this.state.dataSource.credit_limit}
                    <br />

                </Card>
                <br /><br />



            </div>
        );
    }
}

export default Userinformation;
