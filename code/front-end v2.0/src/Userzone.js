import { Layout, Menu, Icon ,Button} from 'antd';
import React, { Component } from 'react';
import {Link, Control} from 'react-keeper'
import './App.css';

const { Content,Sider } = Layout;
const SubMenu = Menu.SubMenu;


class Userzone extends Component {
    constructor(props) {
        super(props);

        this.state = {
            collapsed: false,
            state: 0,
            username: '',
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
        if (this.state.username !== ''){


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
                                    title={<span><Icon type="user"/><span>个人中心</span></span>}
                                >
                                    <Menu.Item key="1"><Link to={"userzone/userinformation"}>个人信息</Link>
                                    </Menu.Item>
                                    <Menu.Item key="2"><Link to={"userzone/creditapply"}>额度申请</Link>
                                    </Menu.Item>
                                    <Menu.Item key="3"><Link to={"userzone/cardmanagement"}>银行卡管理</Link>
                                    </Menu.Item>
                                </SubMenu>
                                <SubMenu
                                    key="sub2"
                                    title={<span><Icon type="bank"/><span>我要理财</span></span>}
                                >
                                    <Menu.Item key="4"><Link to={"userzone/debtsmanagement"}>债权管理</Link></Menu.Item>
                                    <Menu.Item key="5"><Link to={"userzone/itemmanagement"}>理财产品管理</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu
                                    key="sub3"
                                    title={<span><Icon type="wallet"/><span>我要借款</span></span>}
                                >
                                    <Menu.Item key="6"><Link to={"userzone/loanapply"}>借款申请</Link></Menu.Item>
                                    <Menu.Item key="7"><Link to={"userzone/loanmanagement"}>借款管理</Link></Menu.Item>
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
    else{
        return (
            <div>
                <div style={{marginLeft:"40%",marginTop:"20"}}>您尚未登录</div>
                <Button type = 'primary' onClick = {this.goLogin.bind(this)}>立即登录</Button>
            </div>
        );
    }
    }




}
export default Userzone;
