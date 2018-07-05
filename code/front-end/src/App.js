import React, { Component } from 'react';
import './App.css';
import {HashRouter, Route, Control,Link} from 'react-keeper'
import { Layout, Menu, Breadcrumb, Icon } from 'antd';
import Loanapply from './Loanapply';
import Usermanagement from './Usermanagement';
import Debtsmanagement from './Debtsmanagement';
import  Itemmanagement from './Itemmanagement';
import Loanmanagement from './Loanmanagement';
import Debtstransfer from './Debtstransfer';
import Login from './Login';


const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;

class App extends Component {
    render() {
        return (
            <HashRouter>

            <Layout>
                <Header className="header">
                    <div className="logo" />
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        defaultSelectedKeys={['2']}
                        style={{ lineHeight: '64px' }}
                    >
                        <Menu.Item key="1">首页</Menu.Item>
                        <Menu.Item key="2"><Link to='/login'>登录</Link></Menu.Item>
                        <Menu.Item key="3">注册</Menu.Item>
                        <Menu.Item key="4">帮助</Menu.Item>
                    </Menu>
                </Header>
                <Content style={{ padding: '0 50px' }}>
                    <Breadcrumb style={{ margin: '16px 0' }}>
                        <Breadcrumb.Item>Home</Breadcrumb.Item>
                        <Breadcrumb.Item>List</Breadcrumb.Item>
                        <Breadcrumb.Item>App</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultSelectedKeys={['1']}
                                defaultOpenKeys={['sub1']}
                                style={{ height: '100%' }}
                            >
                                <SubMenu key="sub1" title={<span><Icon type="user" />我的账户</span>}>
                                    <Menu.Item key="1"><Link to='/usermanagement'>个人信息管理</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub2" title={<span><Icon type="laptop" />我要理财</span>}>
                                    <Menu.Item key="2"><Link to='/debtsmanagement'>债权管理</Link></Menu.Item>
                                    <Menu.Item key="3"><Link to='itemmanagement'>理财产品管理</Link></Menu.Item>
                                    <Menu.Item key="4"><Link to='/debtstransfer'>债权转让</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub3" title={<span><Icon type="notification" />我要借款</span>}>
                                    <Menu.Item key="5"><Link to='/loanapply'>借款申请</Link></Menu.Item>
                                    <Menu.Item key="6"><Link to='/loanmanagement'>借款管理</Link></Menu.Item>
                                </SubMenu>
                            </Menu>
                        </Sider>
                        <Content style={{ padding: '0 24px', minHeight: 500 }}>
                            <Route path="/loanapply" component={ Loanapply }/>
                            <Route path="/usermanagement" component={ Usermanagement }/>
                            <Route path="/debtsmanagement" component={ Debtsmanagement }/>
                            <Route path="/itemmanagement" component={ Itemmanagement }/>
                            <Route path="/debtstransfer" component={ Debtstransfer }/>
                            <Route path="/loanmanagement" component={ Loanmanagement }/>
                            <Route path="/login" component={ Login }/>
                        </Content>
                    </Layout>
                </Content>
                <Footer style={{ textAlign: 'center' }}>
                    Ant Design ©2016 Created by Ant UED
                </Footer>
            </Layout>
            </HashRouter>
        )
    }
}

export default App;