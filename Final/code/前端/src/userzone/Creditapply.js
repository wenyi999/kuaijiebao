import { Form, Input, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button, AutoComplete } from 'antd';
import React, {Component} from 'react';
import {message} from "antd/lib/index";
import {Control} from "react-keeper";
import $ from 'jquery';
import './Loanapply.css';
const FormItem = Form.Item;
const Option = Select.Option;
const AutoCompleteOption = AutoComplete.Option;


class Creditapply extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username:'',
            sys_inf: '',
            //dataSource: []
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                $.ajax({
                    type: 'POST',
                    url: "/credit",
                    data: {
                        creditStatus:4,
                        username:this.state.username,
                        edubg:values.edubg,
                        job:values.job,
                        income:values.income,
                        ask:values.ask,
                    },
                    success: function (data) {
                        if (data === 'asking') {
                            message.info("申请成功！！！");
                            this.setState({
                                sys_inf: data
                            });
                        }
                        else if (data === 'has asked') {
                            message.info("您已申请，请等待申请结果!");
                            this.setState({
                                sys_inf: data
                            });
                        }
                        else {
                            message.info("未知错误");
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
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
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
        let username = this.getCookie('username')
        this.setState({username:username})

    }

    render() {
        const { getFieldDecorator } = this.props.form;
        const { autoCompleteResult } = this.state;

        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 8 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 16 },
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
        const prefixSelector = getFieldDecorator('prefix', {
            initialValue: '86',
        })(
            <Select style={{ width: 70 }}>
                <Option value="86">+86</Option>
                <Option value="87">+87</Option>
            </Select>
        );



        return (

            <Form onSubmit={this.handleSubmit}  className="loanapply-form"  style={{padding:"20px"}} >
                <FormItem
                    {...formItemLayout}
                    label="用户名"
                >
                    {this.state.username}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="学历"
                >
                    {getFieldDecorator('edubg', {
                        rules: [{ required: true, message: '请输入学历!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="学校"
                >
                    {getFieldDecorator('job', {
                        rules: [{ required: true, message: '请输入学校!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="月收入"
                >
                    {getFieldDecorator('income', {
                        rules: [{ required: true, message: '请输入月收入!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="申请额度"
                >
                    {getFieldDecorator('ask', {
                        rules: [{ required: true, message: '请输入申请额度!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>


                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">提交</Button>
                </FormItem>
            </Form>

        );
    }

}

Creditapply = Form.create()(Creditapply);




export default Creditapply;