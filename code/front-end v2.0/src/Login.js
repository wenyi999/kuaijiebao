import { Form, Icon, Input, Button, Checkbox } from 'antd';
import React, {Component} from 'react';
import {message} from "antd/lib/index";
import {Control} from "react-keeper";
import $ from 'jquery';
import './Login.css';


const FormItem = Form.Item



class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',

        };
        this.handleSubmit = this.handleSubmit.bind(this);


    }


    /*Control.go('/userzone/'+ values.username, {name: 'React-Keeper'})*/

    setCookie = (cname,cvalue,exmin) => {

        let d = new Date();
        d.setTime(d.getTime()+(exmin*60*1000));
        let expires = "expires="+d.toGMTString();
        document.cookie = cname + "=" + cvalue + "; " + expires + "; "+ "path=/";
        return;
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


    deleteCookie=(cname)=>{

        document.cookie = cname +"=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/"
        message.info('退出成功')
        Control.go('/home', {name: 'React-Keeper'})
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {

                console.log('Received values of form: ', values);
                console.log('姓名: ', values.username)
                console.log('密码: ', values.password)
                $.ajax({
                    type: 'GET',
                    url: "/UserLog",
                    data: {
                        username: values.username,
                        password: values.password,
                    },
                    success: function (data) {
                        if (data === 'USER') {
                            message.info("登录成功！！！");


                            console.log(values.username);
                            this.setCookie("username",values.username,15)

                            if (values.username==='admin'){Control.go('/adminzone', {name:'React-keeper'})}
                            else{
                            Control.go('/userzone', {name: 'React-Keeper'})}
                            console.log(values.username);
                        }
                        if (data === 'WRONGPWD' || data === 'NULL') {
                            message.info("密码错误，请检查你的用户名和密码！！！");
                        }

                    }.bind(this),


                });

            }
        });
    }

    componentWillMount(){
        let username = this.getCookie("username")
        this.setState({
            username:username
        })
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        if (this.state.username === ""){
            return (
                <Form onSubmit={this.handleSubmit} className="login-form" style={{marginLeft: "35%", padding: "50px"}}>
                    <FormItem>
                        {getFieldDecorator('username', {
                            rules: [{required: true, message: '请输入用户名!'}],
                        })(
                            <Input prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>} placeholder="用户名"/>
                        )}
                    </FormItem>
                    <FormItem>
                        {getFieldDecorator('password', {
                            rules: [{required: true, message: '请输入密码!'}],
                        })(
                            <Input prefix={<Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>} type="password"
                                   placeholder="密码"/>
                        )}
                    </FormItem>
                    <FormItem>
                        {getFieldDecorator('remember', {
                            valuePropName: 'checked',
                            initialValue: true,
                        })(
                            <Checkbox>记住我</Checkbox>
                        )}
                        <a className="login-form-forgot" href="">忘记密码</a>
                        <Button type="primary" htmlType="submit" className="login-form-button">
                            登录
                        </Button>
                    </FormItem>
                </Form>

            );
        }
        else {
            return (
                <div >
                    <div style={{marginLeft:"40%",marginTop:"20"}}>您已登录</div>
                    <Button type = 'primary' style={{marginLeft:"40%",marginTop:"20"}} onClick = {this.deleteCookie.bind(this,'username')}>退出登录</Button>
                </div>
            );
        }
    }
}

Login = Form.create()(Login);

export default Login;

