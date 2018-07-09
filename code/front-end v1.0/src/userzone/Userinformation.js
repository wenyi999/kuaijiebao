
import React, {Component} from 'react';




import './App.css';

import { Card,Radio,Input,Button } from 'antd';

const RadioGroup = Radio.Group;

const user = {
    username:'SJTU GROUP22 member1',
    id: 123456789123456789,
    phone: 11111111111,
    credit_level: '100',
    credit_limit: 10000,
    card: [
        1111111111111111111,2222222222222222222
    ]
}

class Userinformation extends Component {
    state = {
        value: 1,
    }
    onChange = (e) => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    }
    render() {
        return (
            <div style={{padding:"50px"}}>

                <Card
                    style={{ width: '50%' }}
                    title="用户名"
                >
                    <Input defaultValue = {user.username} />
                </Card>
                <br /> <br />
                <Card
                    style={{ width: '50%' }}
                    title="性别"
                >
                    <RadioGroup onChange={this.onChange} value={this.state.value}>
                        <Radio value={1}>男</Radio>
                        <Radio value={2}>女</Radio>
                    </RadioGroup>
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="身份证"
                >
                    <Input defaultValue={user.id} />
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="手机号码"
                >
                    <Input defaultValue={user.phone} />
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="信用"
                >
                    信用评级：{user.credit_level}
                    <br />
                    信用额度：{user.credit_limit}
                    <br />
                    <Button type = "primary">申请额度</Button>
                </Card>
                <br /><br />
                <Card
                    style={{ width: '50%' }}
                    title="银行卡"
                >
                    {
                        user.card.map(function(card_id){
                            return <div>
                                <li key = {card_id}>{card_id}</li>

                                <Button>解绑</Button>
                            </div>;
                        })
                    }
                    <br />
                    <Input style = {{width :'50%'}}placeholder="请输入银行卡号" />
                    <br /><br />
                    <Button type = "primary">绑定</Button>
                </Card>
                <br /><br />
                <Button type = "primary" style={{marginLeft:"20%"}}>保存</Button>
            </div>
        );
    }
}

export default Userinformation;
