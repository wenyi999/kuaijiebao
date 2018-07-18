import { Form, Icon, Input, Button, Checkbox } from 'antd';
import React, {Component} from 'react';
import {message} from "antd/lib/index";
import {Control} from "react-keeper";
import $ from 'jquery';
import './Login.css';
import emitter from "./event"

const FormItem = Form.Item

const login_success = (username) => {
    console.log("begin_emit")
    emitter.emit("CallUsername", username)
}
    /*return () => {
        console.log("begin_emit")
        // 触发自定义事件
        emitter.emit("CallUsername",username)
    }*/



class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            sys_inf: '',

        };
        this.handleSubmit = this.handleSubmit.bind(this);


    }


    /*Control.go('/userzone/'+ values.username, {name: 'React-Keeper'})*/


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
                            Control.go('/userzone', {name: 'React-Keeper'})
                            console.log(values.username);
                            login_success(values.username);

                            this.setState({
                                sys_inf: data
                            });
                        }
                        if (data === 'WRONGPWD' || data === 'NULL') {
                            message.info("密码错误，请检查你的用户名和密码！！！");
                            this.setState({
                                sys_inf: data
                            });
                        }

                    }.bind(this),


                });

            }
        });
    }


    render() {
        const {getFieldDecorator} = this.props.form;
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
}

Login = Form.create()(Login);

export default Login;

