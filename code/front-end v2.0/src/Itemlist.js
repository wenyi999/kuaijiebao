import React, { Component } from 'react';
import {List, Form, Input, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button,Popconfirm,Table} from 'antd';
import {message} from "antd/lib/index";
import $ from 'jquery'
import './App.css';
import {Control} from "react-keeper";

const FormItem = Form.Item;

const EditableContext = React.createContext();

function onChange(value) {
    console.log('changed', value);
}

const EditableRow = ({ form, index, ...props }) => (
    <EditableContext.Provider value={form}>
        <tr {...props} />
    </EditableContext.Provider>
);

const EditableFormRow = Form.create()(EditableRow);

class EditableCell extends React.Component {
    state = {
        editing: false,
    }

    componentDidMount() {
        if (this.props.editable) {
            document.addEventListener('click', this.handleClickOutside, true);
        }
    }

    componentWillUnmount() {
        if (this.props.editable) {
            document.removeEventListener('click', this.handleClickOutside, true);
        }
    }

    toggleEdit = () => {
        const editing = !this.state.editing;
        this.setState({ editing }, () => {
            if (editing) {
                this.input.focus();
            }
        });
    }

    checkInput = (rule, value, callback) => {
        if (value && value < 1000) {
            callback('不能低于1000的投资金额');
        }
        else if (isNaN(value) === true){
            callback('请输入数字');
        }
        else {
            callback();
        }
    }


    handleClickOutside = (e) => {
        const { editing } = this.state;
        if (editing && this.cell !== e.target && !this.cell.contains(e.target)) {
            this.save();
        }
    }

    save = () => {
        const { record, handleSave } = this.props;
        this.form.validateFields((error, values) => {
            if (error) {
                return;
            }
            this.toggleEdit();
            handleSave({ ...record, ...values });
        });
    }





    render() {
        const { editing } = this.state;
        const {
            editable,
            dataIndex,
            title,
            record,
            index,
            handleSave,
            ...restProps
        } = this.props;
        return (
            <td ref={node => (this.cell = node)} {...restProps}>
                {editable ? (
                    <EditableContext.Consumer>
                        {(form) => {
                            this.form = form;
                            return (
                                editing ? (
                                    <FormItem style={{ margin: 0 }}>
                                        {form.getFieldDecorator(dataIndex, {
                                            rules: [{
                                                required: true,
                                                message: `money is required.`,
                                            },{
                                                validator:this.checkInput
                                            }
                                            ],
                                            initialValue: record[dataIndex],
                                        })(
                                            <Input
                                                ref={node => (this.input = node)}
                                                onPressEnter={this.save}
                                            />
                                        )}
                                    </FormItem>
                                ) : (
                                    <div
                                        className="editable-cell-value-wrap"
                                        style={{ paddingRight: 24 }}
                                        onClick={this.toggleEdit}
                                    >
                                        {restProps.children}
                                    </div>
                                )
                            );
                        }}
                    </EditableContext.Consumer>
                ) : restProps.children}
            </td>
        );
    }
}


class Itemlist extends Component {
    constructor(props) {
        super(props);
        this.columns = [ {
            title: '商品名称',
            dataIndex: 'item_name',
        },{
            title: '投资金额（可修改）',
            dataIndex: 'price',
            editable: true,
        },{
            title: '利率',
            dataIndex: 'item_rate',
        },{
            title: '方式',
            dataIndex: 'operation',
            render: (text, record) => {
                return (
                    <Popconfirm title="确认以该金额购买吗?" onConfirm={() => this.buy(record.item_name)}>
                        <a href="javascript:;">购买</a>
                    </Popconfirm>
                );
            },
        }];
        this.state = {
            username:"",
            dataSource: []
        };
        this.loadlist = this.loadlist.bind(this);
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



    loadlist(){
        const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'GET',
            url:'/goods',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
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
    buy= (item_name) => {
        let username = this.getCookie('username')
        if (username === ""){
            console.log("in control")
            message.info("请先登录")
            Control.go('/login', {name: 'React-Keeper'});
            return;
        }
        const dataSource = [...this.state.dataSource];
        const target = dataSource.find(item => item.item_name === item_name);
        $.ajax({
            type:"POST",
            url:'/buy',
            data:{
                username:username,
                item_name: target.item_name,
                amount: target.price
            },
            success:function(data){
                message.info("购买成功");
            }.bind(this),
            error:function(data){
                console.log(data)
            }
        });
    }

    handleSave = (row) => {
        const newData = [...this.state.dataSource];
        const index = newData.findIndex(item => row.item_name === item.item_name);
        const item = newData[index];
        newData.splice(index, 1, {
            ...item,
            ...row,
        });
        this.setState({ dataSource: newData });
    }
    componentWillMount(){

        this.loadlist()
    }
    render(){
        const {dataSource} = this.state;
        const components = {
            body: {
                row: EditableFormRow,
                cell: EditableCell,
            },
        };
        const columns = this.columns.map((col) => {
            if (!col.editable) {
                return col;
            }
            return {
                ...col,
                onCell: record => ({
                    record,
                    editable: col.editable,
                    dataIndex: col.dataIndex,
                    title: col.title,
                    handleSave: this.handleSave,
                }),
            };
        });
        return (
            <div>
                <Table
                    components={components}
                    rowClassName={() => 'editable-row'}
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

export default Itemlist;