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
import Help from './Help';
import Adminzone from './Adminzone';
import Userinformation from './userzone/Userinformation';
import Debtsmanagement from './userzone/Debtsmanagement';
import Itemmanagement from './userzone/Itemmanagement';
import Loanapply from './userzone/Loanapply';
import Loanmanagement from './userzone/Loanmanagement';
import Creditapply from './userzone/Creditapply';
import Cardmanagement from './userzone/Cardmanagement';
import {message} from "antd/lib/index";
import Creditcheckout from './adminzone/Creditcheckout';
import Creditmanagement from './adminzone/Creditmanagement';
import Debtcount from './adminzone/Debtcount';
import Paycount from './adminzone/Paycount';
import Personitemcount from './adminzone/Personitemcount';
import Itemcount from './adminzone/Itemcount';
const { Header, Content, Footer } = Layout;

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username:"",
        };
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
                        defaultSelectedKeys={['1']}
                        style={{ lineHeight: '64px' }}
                    >
                        <Menu.Item key="1"><Link to="home">首页</Link></Menu.Item>
                        <Menu.Item key="2"><Link to="login">登录</Link></Menu.Item>
                        <Menu.Item key="3"><Link to="register">注册</Link></Menu.Item>
                        <Menu.Item key="4"><Link to={"debtsinformation"}>我要出借</Link></Menu.Item>
                        <Menu.Item key="5"><Link to={"itemlist"} >理财商城</Link></Menu.Item>
                        <Menu.Item key="6"><Link to={"userzone"} >个人中心</Link></Menu.Item>
                        <Menu.Item key="7"><Link to="help">帮助</Link></Menu.Item>
                        <Menu.Item key="8"><Link to={"adminzone"} >管理员</Link></Menu.Item>
                    </Menu>
                </Header>
                <Content style={{ padding: '0 50px' }}>

                    <div style={{ background: '#fff', minHeight: 500}}>
                        <Route  index path="/home" component={ Home }/>
                        <Route  path="/login" component={ Login } />
                        <Route  path="/register" component={ Register }/>
                        <Route  path="/userzone" component={ Userzone }>
                            <Route path="/userinformation" component={Userinformation}/>
                            <Route path="/debtsmanagement" component={Debtsmanagement}/>
                            <Route path="/itemmanagement" component={Itemmanagement}/>
                            <Route path="/loanapply" component={Loanapply}/>
                            <Route path="/loanmanagement" component={Loanmanagement}/>
                            <Route path="/creditapply" component={Creditapply}/>
                            <Route path="/cardmanagement" component={Cardmanagement}/>
                        </Route>
                        <Route  path="/itemlist" component={ Itemlist }/>
                        <Route  path="/debtsinformation" component={ Debtsinformation }/>
                        <Route  path="/help" component={ Help } />
                        <Route  path="/adminzone" component={ Adminzone }>
                            <Route path="/creditcheckout" component={Creditcheckout}/>
                            <Route path="/creditmanagement" component={Creditmanagement}/>
                            <Route path="/debtcount" component={Debtcount}/>
                            <Route path="/paycount" component={Paycount}/>
                            <Route path="/personitemcount" component={Personitemcount}/>
                            <Route path="/itemcount" component={Itemcount}/>
                        </Route>

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
