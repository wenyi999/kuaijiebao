import './App.css';
import React, { Component } from 'react';
import { Layout, Menu} from 'antd';
import {HashRouter, Route,Link} from 'react-keeper'
import Login from './Login';
import Register from './Register';
import Home from './Home';
import Userzone from './Userzone';
import Itemlist from './Itemlist';
import Debtsinformation from './Debtsinformation';
import emitter from "./event"

const { Header, Content, Footer } = Layout;

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username:"null",
        };
    }
    componentDidMount(){

        this.eventEmitter = emitter.addListener("CallUsername",(username)=>{ this.setState({ username:username }) }); }

        componentWillUnmount(){
        emitter.removeListener(this.eventEmitter);
    }






    render() {
        return (
            <HashRouter>
            <Layout className="layout">
                <Header>
                    <div className="logo" />
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        defaultSelectedKeys={['2']}
                        style={{ lineHeight: '64px' }}
                    >
                        <Menu.Item key="1"><Link to="home">首页</Link></Menu.Item>
                        <Menu.Item key="2"><Link to="login">登录</Link></Menu.Item>
                        <Menu.Item key="3"><Link to="register">注册</Link></Menu.Item>
                        <Menu.Item key="4"><Link to={"debtsinformation/"+ this.state.username}>我要出借</Link></Menu.Item>
                        <Menu.Item key="5"><Link to={"itemlist/"+ this.state.username} >理财商城</Link></Menu.Item>
                        <Menu.Item key="6"><Link to={"userzone"} >个人中心</Link></Menu.Item>
                        <Menu.Item key="7">帮助</Menu.Item>
                    </Menu>
                </Header>
                <Content style={{ padding: '0 50px' }}>

                    <div style={{ background: '#fff', minHeight: 500}}>


                          <Route  path="/login" component={ Login } />
                          <Route  path="/register" component={ Register }/>
                          <Route  path="/home" component={ Home }/>
                          <Route  path="/userzone" component={ Userzone }/>
                          <Route  path="/itemlist/:id" component={ Itemlist }/>
                          <Route  path="/debtsinformation/:id" component={ Debtsinformation }/>

                </div>
                </Content>
                <Footer style={{ textAlign: 'center' }}>
                    KuaiJieBao ©2018 Created by Team 22
                </Footer>
            </Layout>
            </HashRouter>)

    }
}

export default App;
