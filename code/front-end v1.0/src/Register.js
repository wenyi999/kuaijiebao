import { Form, Input, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button, AutoComplete } from 'antd';
import React, {Component} from 'react';
import './Register.css';
import {message} from "antd/lib/index";
import $ from 'jquery';
import {Control} from "react-keeper";

const FormItem = Form.Item;
const Option = Select.Option;
const AutoCompleteOption = AutoComplete.Option;



class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sys_inf: '',
            //dataSource: []
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit  = (e) =>{
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {


                console.log('Received values of form: ', values)
                 console.log('用户名: ', values.email)
                 console.log('密码: ', values.password)
                 console.log('手机: ', this.props.form.getFieldValue("phone"))
                console.log('真实姓名: ', this.props.form.getFieldValue("name"))
                 console.log('身份证号: ', this.props.form.getFieldValue("id"))




                $.ajax({
                    type: 'POST',
                    url: "/UserLog",
                    data: {
                        username: values.email,
                        password: values.password,
                        phone: this.props.form.getFieldValue("phone"),
                        name: values.name,
                        ID: values.id,
                        credit: 60,
                        line_of_credit: 1000
                    },
                    success: function (data) {
                        if (data === 'ADDUSER') {
                            message.info("注册成功！！！");
                            Control.go('/login', {name: 'React-Keeper'})
                            this.setState({
                                sys_inf: data
                            });
                        }
                        if (data === 'USERERROR') {
                            message.info("用户名已存在！！！");
                            this.setState({
                                sys_inf: data
                            });
                        }

                    }.bind(this),

                });
            }
        });
    }

    handleConfirmBlur = (e) => {
        const value = e.target.value;
        this.setState({confirmDirty: this.state.confirmDirty || !!value});
    }

    compareToFirstPassword = (rule, value, callback) => {
        const form = this.props.form;
        if (value && value !== form.getFieldValue('password')) {
            callback('两次输入密码不一致!');
        } else {
            callback();
        }
    }

    validateToNextPassword = (rule, value, callback) => {
        const form = this.props.form;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], {force: true});
        }
        callback();
    }


    render() {
        const {getFieldDecorator} = this.props.form;
        const {autoCompleteResult} = this.state;

        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 8},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 16},
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 16,
                    offset: 8,
                },
            },
        };


        return (
            <Form onSubmit={this.handleSubmit} className="register-form" style={{padding: "20px"}}>
                <FormItem
                    {...formItemLayout}
                    label="用户名(邮箱）"
                >
                    {getFieldDecorator('email', {
                        rules: [{
                            type: 'email', message: '输入邮箱不合法!',
                        }, {
                            required: true, message: '请输入用户名!',
                        }],
                    })(
                        <Input/>
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="密码"
                >
                    {getFieldDecorator('password', {
                        rules: [{
                            required: true, message: '请输入密码!',
                        }, {
                            validator: this.validateToNextPassword,
                        }],
                    })(
                        <Input type="password"/>
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="确认密码"
                >
                    {getFieldDecorator('confirm', {
                        rules: [{
                            required: true, message: '请输入确认密码!',
                        }, {
                            validator: this.compareToFirstPassword,
                        }],
                    })(
                        <Input type="password" onBlur={this.handleConfirmBlur}/>
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="真实姓名"
                >
                    {getFieldDecorator('name', {
                        rules: [{required: true, message: '请输入真实姓名!', whitespace: true}],
                    })(
                        <Input style={{width: '100%'}}/>
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="手机号"
                >
                    {getFieldDecorator('phone', {
                        rules: [{required: true, message: '请输入手机号!'}],
                    })(
                        <Input style={{width: '100%'}}/>
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label=" 身份证号码"
                >
                    {getFieldDecorator('id', {
                        rules: [{required: true, message: '请输入身份证号码!'}],
                    })(
                        <Input style={{width: '100%'}}/>
                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="验证码"
                >
                    <Row gutter={8}>
                        <Col span={12}>
                            {getFieldDecorator('captcha', {
                                rules: [{required: true, message: '请输入验证码!'}],
                            })(
                                <Input/>
                            )}
                        </Col>
                        <Col span={12}>
                            <Button>获取验证码</Button>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem {...tailFormItemLayout}>
                    {getFieldDecorator('agreement', {
                        valuePropName: 'checked',
                    })(
                        <Checkbox>我同意相关协议</Checkbox>
                    )}
                </FormItem>
                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">注册</Button>
                </FormItem>
            </Form>
        );
    }
}



Register = Form.create()(Register);




export default Register;