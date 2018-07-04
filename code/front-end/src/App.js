import React, { Component } from 'react';
import './App.css';
import { Layout, Menu, Breadcrumb, Icon } from 'antd';

const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;

class App extends Component {
  render() {
    return (
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
        <Menu.Item key="2">登录</Menu.Item>
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
            <Menu.Item key="1">个人信息管理</Menu.Item>
          </SubMenu>
          <SubMenu key="sub2" title={<span><Icon type="laptop" />我要理财</span>}>
            <Menu.Item key="2">债权管理</Menu.Item>
            <Menu.Item key="3">理财产品管理</Menu.Item>
            <Menu.Item key="4">债权转让</Menu.Item>
          </SubMenu>
          <SubMenu key="sub3" title={<span><Icon type="notification" />我要借款</span>}>
            <Menu.Item key="5">借款申请</Menu.Item>
            <Menu.Item key="6">借款管理</Menu.Item>
          </SubMenu>
          </Menu>
        </Sider>
        <Content style={{ padding: '0 24px', minHeight: 500 }}>
          Content
        </Content>
      </Layout>
    </Content>
    <Footer style={{ textAlign: 'center' }}>
       Ant Design ©2016 Created by Ant UED
    </Footer>
    </Layout>
    )
  }
}

export default App;
