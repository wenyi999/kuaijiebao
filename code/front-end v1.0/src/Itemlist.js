import React, { Component } from 'react';
import { List, Card,Button,InputNumber} from 'antd';
import $ from 'jquery'
import './App.css';
import {message} from "antd/lib/index";

function onChange(value) {
    console.log('changed', value);
}

class Itemlist extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username:"",
            dataSource: []
        };
    this.loadlist = this.loadlist.bind(this);
    }
    loadlist(){
        //const dataSource = [...this.state.dataSource];
        $.ajax({
            type:'GET',
            url:'/goods',
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
  /*  buy(name,value){
        $.ajax({
            type:"POST",
            url:'/buy',
            data:{
                username:'admin',
                item_name: document.getElementById('card').title,
                amount:document.getElementById('val').value,
            },
            success:function(data){
                message.info("success");
            }.bind(this),
            error:function(data){
                console.log(data)
            }

        })
    } */
    componentWillMount(){
        this.loadlist()
    }
    render(){

        const { dataSource } = this.state;
        console.log(dataSource)
        return (
            <List
                pagination={{
                    onChange: (page) => {
                        console.log(page);
                    },
                    pageSize: 6,
                }}
                grid={{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 3, xxl: 3 }}
                dataSource={dataSource}

                renderItem={item => (
                    <List.Item>
                        <Card id = 'card' title={item.item_name}>
                            购买金额: {item.price}<br />
                            <br />
                            利率: {item.item_rate}<br />
                            <br />
                            购买数量：<InputNumber id = 'val' min={1} max={100} defaultValue={1} onChange={onChange} />
                            <Button
                            type = "primary">
                            购买
                            </Button>
                        </Card>
                    </List.Item>
                )}
            />
        )
    }
}

export default Itemlist;