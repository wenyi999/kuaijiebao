import { Layout, Menu, Icon } from 'antd';
import React, { Component } from 'react';
import {HashRouter, Route,Link} from 'react-keeper'

import './App.css';
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
                        <Menu.Item key="1">
                            <Icon type="user" />
                            <span>个人信息</span>
                        </Menu.Item>
                        <SubMenu
                            key="sub1"
                            title={<span><Icon type="bank" /><span>我要理财</span></span>}
                                >
                            <Menu.Item key="2">我要出借</Menu.Item>
                            <Menu.Item key="3">债权管理</Menu.Item>
                            <Menu.Item key="4">购买理财产品</Menu.Item>
                            <Menu.Item key="5">理财产品管理</Menu.Item>
                        </SubMenu>
                        <SubMenu
                            key="sub2"
                            title={<span><Icon type="wallet" /><span>我要借款</span></span>}
                        >
                            <Menu.Item key="6">借款申请</Menu.Item>
                            <Menu.Item key="7">借款管理</Menu.Item>
                        </SubMenu>
                        <Menu.Item key="9">
                            <Icon type="question-circle" />
                            <span>帮助</span>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout>
                        <Content style={{ margin: '0 16px' }}>

                           hello
                    </Content>

                </Layout>
            </Layout>
            </HashRouter>
        );
    }
}
export default Userzone;