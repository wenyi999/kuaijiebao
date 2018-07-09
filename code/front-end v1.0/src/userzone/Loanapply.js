import { Form, Input, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button, AutoComplete } from 'antd';
import React, {Component} from 'react';
import './Loanapply.css';
const FormItem = Form.Item;
const Option = Select.Option;
const AutoCompleteOption = AutoComplete.Option;


class Loanapply extends Component {
    state = {
        confirmDirty: false,
        autoCompleteResult: [],
    };

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    }

    handleConfirmBlur = (e) => {
        const value = e.target.value;
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
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
                     张三
                </FormItem>

                <FormItem
                        {...formItemLayout}
                        label="信用额度"
                    >
                        10000
                    </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="借款金额"
                >
                    {getFieldDecorator('money', {
                        rules: [{ required: true, message: '请输入借款金额!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="借款时限(月为单位）"
                >
                    {getFieldDecorator('deadline', {
                        rules: [{ required: true, message: '请输入借款时限!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="利率"
                >
                    {getFieldDecorator('interest rate', {
                        rules: [{ required: true, message: '请输入利率!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>

                <FormItem
                    {...formItemLayout}
                    label="借款理由"
                >
                    {getFieldDecorator('reason', {
                        rules: [{ required: true, message: '请输入借款理由!' }],
                    })(
                        <Input  style={{ width: '100%' }} />
                    )}
                </FormItem>





                <FormItem {...tailFormItemLayout}>
                    {getFieldDecorator('agreement', {
                        valuePropName: 'checked',
                    })(
                        <Checkbox>已阅读并同意借款说明</Checkbox>
                    )}
                </FormItem>
                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">提交</Button>
                </FormItem>
            </Form>

        );
    }

}

Loanapply = Form.create()(Loanapply);




export default Loanapply;