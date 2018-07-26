import { Layout, Menu, Icon ,Button} from 'antd';
import React, { Component } from 'react';
import {Link, Control} from 'react-keeper'
import './App.css';
import {message} from "antd/lib/index";

const { Content,Sider } = Layout;
const SubMenu = Menu.SubMenu;


class Adminzone extends Component {
    constructor(props) {
        super(props);

        this.state = {
            collapsed: false,
            state: 0,
            username: ''
        };

    }


    onCollapse = (collapsed) => {
        console.log(collapsed);
        this.setState({collapsed});
    }
    getCookie = (cname) => {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf(name) === 0) return c.substring(name.length, c.length);
        }
        return "";
    }

    componentWillMount() {
        let username = this.getCookie('username')
        this.setState({username: username})

    }

    goLogin = () => {
        Control.go('/login', {name: 'React-Keeper'})
    }


    render() {
        if (this.state.username === 'admin') {

            return (



                    <Layout style={{minHeight: '100vh'}}>
                        <Sider
                            collapsible
                            collapsed={this.state.collapsed}
                            onCollapse={this.onCollapse}
                        >
                            <div className="logo"/>
                            <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                                <SubMenu
                                    key="sub1"
                                    title={<span><Icon type="user"/><span>信用管理</span></span>}
                                >
                                    <Menu.Item key="1"><Link to={"adminzone/creditcheckout"}>信用审核</Link></Menu.Item>
                                    <Menu.Item key="2"><Link to={"adminzone/creditmanagement"}>信用管理</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu
                                    key="sub2"
                                    title={<span><Icon type="bank"/><span>统计</span></span>}
                                >
                                    <Menu.Item key="3"><Link to={"adminzone/debtcount"}>借款统计</Link></Menu.Item>
                                    <Menu.Item key="4"><Link to={"adminzone/paycount"}>支出统计</Link></Menu.Item>
                                    <Menu.Item key="5"><Link to={"adminzone/personitemcount"}>理财统计</Link></Menu.Item>
                                    <Menu.Item key="6"><Link to={"adminzone/itemcount"}>理财产品统计</Link></Menu.Item>
                                </SubMenu>


                            </Menu>
                        </Sider>
                        <Layout>
                            <Content>
                                {this.props.children}
                            </Content>

                        </Layout>
                    </Layout>



            );
        }
        else {
            return (
                <div>
                    <div style={{marginLeft: "40%", marginTop: "20"}}>请以管理员身份登录</div>
                    <Button type='primary' style={{marginLeft:"40%",marginTop:"20"}}  onClick={this.goLogin.bind(this)}>立即登录</Button>
                </div>
            );
        }


    }
}

export default Adminzone;
