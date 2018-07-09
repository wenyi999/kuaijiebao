import React, { Component } from 'react';
import './App.css';
import {HashRouter, Route, Control,Link} from 'react-keeper'
import { Layout, Menu, Breadcrumb, Icon } from 'antd';
import Borrow from "./Borrow";
import Itemmanagement from "./Itemmanagement";
import Buyitem from "./Buyitem";
import Loanmanagement from "./Loanmanagement";
import Usermanagement from "./Usermanagement";
import Debtsmanagement from "./Debtsmanagement";
import Loanapply from "./Loanapply";

const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;

class Home extends Component {
    render() {
        return (
            <HashRouter>
                <Layout style={{padding: '24px 0', background: '#fff'}}>
                    <Sider width={200} style={{background: '#fff'}}>
                        <Menu
                            mode="inline"
                            defaultSelectedKeys={['1']}
                            defaultOpenKeys={['sub1']}
                            style={{height: '100%'}}
                        >
                            <SubMenu key="sub1" title={<span><Icon type="user"/>我的账户</span>}>
                                <Menu.Item key="1"><Link to='/usermanagement'>个人信息管理</Link></Menu.Item>
                            </SubMenu>
                            <SubMenu key="sub2" title={<span><Icon type="laptop"/>我要理财</span>}>
                                <Menu.Item key="2"><Link to='/borrow'>我要出借</Link></Menu.Item>
                                <Menu.Item key="3"><Link to='/debtsmanagement'>债权管理</Link></Menu.Item>
                                <Menu.Item key="4"><Link to='buyitem'>购买理财产品</Link></Menu.Item>
                                <Menu.Item key="5"><Link to='itemmanagement'>理财产品管理</Link></Menu.Item>
                            </SubMenu>
                            <SubMenu key="sub3" title={<span><Icon type="notification"/>我要借款</span>}>
                                <Menu.Item key="6"><Link to='/loanapply'>借款申请</Link></Menu.Item>
                                <Menu.Item key="7"><Link to='/loanmanagement'>借款管理</Link></Menu.Item>
                            </SubMenu>
                        </Menu>
                    </Sider>
                    <Content style={{padding: '0 24px', minHeight: 500}}>
                        <Route path="/loanapply" component={Loanapply}/>
                        <Route path="/usermanagement" component={Usermanagement}/>
                        <Route path="/debtsmanagement" component={Debtsmanagement}/>
                        <Route path="/itemmanagement" component={Itemmanagement}/>
                        <Route path="/buyitem" component={Buyitem}/>
                        <Route path="/borrow" component={Borrow}/>
                        <Route path="/loanmanagement" component={Loanmanagement}/>
                    </Content>
                </Layout>
            </HashRouter>)
    }
}

export default Home;