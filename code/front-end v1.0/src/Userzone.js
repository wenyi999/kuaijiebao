import { Layout, Menu, Icon } from 'antd';
import React, { Component } from 'react';
import {HashRouter, Route,Link} from 'react-keeper'

import './App.css';
import Userinformation from './userzone/Userinformation';
import Debtsmanagement from './userzone/Debtsmanagement';
import Itemmanagement from './userzone/Itemmanagement';
import Loanapply from './userzone/Loanapply';
import Loanmanagement from './userzone/Loanmanagement';


const { Content,Sider } = Layout;
const SubMenu = Menu.SubMenu;


class Userzone extends Component {
    state = {
        collapsed: false,
    };

    onCollapse = (collapsed) => {
        console.log(collapsed);
        this.setState({ collapsed });
    }

    render() {
        return (
            <HashRouter>
            <Layout style={{ minHeight: '100vh' }}>
                <Sider
                    collapsible
                    collapsed={this.state.collapsed}
                    onCollapse={this.onCollapse}
                >
                    <div className="logo" />
                    <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                        <SubMenu
                            key="sub1"
                            title={<span><Icon type="user" /><span>个人中心</span></span>}
                        >
                            <Menu.Item key="1"><Link to="userzone/userinformation">个人信息</Link>
                        </Menu.Item>
                        </SubMenu>
                        <SubMenu
                            key="sub2"
                            title={<span><Icon type="bank" /><span>我要理财</span></span>}
                        >
                            <Menu.Item key="2"><Link to="userzone/debtsmanagement">债权管理</Link></Menu.Item>
                            <Menu.Item key="3"><Link to="userzone/itemmanagement">理财产品管理</Link></Menu.Item>
                        </SubMenu>
                        <SubMenu
                            key="sub3"
                            title={<span><Icon type="wallet" /><span>我要借款</span></span>}
                        >
                            <Menu.Item key="4"><Link to="userzone/loanapply">借款申请</Link></Menu.Item>
                            <Menu.Item key="5"><Link to="userzone/loanmanagement">借款管理</Link></Menu.Item>
                        </SubMenu>
                        <Menu.Item key="6">
                            <Icon type="question-circle" />
                            <span>帮助</span>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout>
                        <Content >

                            <Route  path="userinformation" component={ Userinformation }/>
                            <Route  path="/debtsmanagement" component={ Debtsmanagement }/>
                            <Route  path="/itemmanagement" component={ Itemmanagement }/>
                            <Route  path="/loanapply" component={ Loanapply }/>
                            <Route  path="/loanmanagement" component={ Loanmanagement }/>

                    </Content>

                </Layout>
            </Layout>
            </HashRouter>
        );
    }
}
export default Userzone;