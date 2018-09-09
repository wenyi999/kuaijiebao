import { Carousel} from 'antd';
import React, {Component} from 'react';


class Home extends Component {
    render() {

        return (


         <Carousel autoplay>
    <div><h3><img src={require('./image/1.jpg')} alt="" width="1250px" height="500px"/></h3></div>
    <div><h3><img src={require('./image/2.jpg')} alt="" width="1250px" height="500px" /></h3></div>
    <div><h3><img src={require('./image/3.jpg')} alt=""  width="1250px" height="500px"/></h3></div>
    <div><h3><img src={require('./image/4.jpg')} alt=""  width="1250px" height="500px"/></h3></div>
         </Carousel>)
    }
}

export default Home;