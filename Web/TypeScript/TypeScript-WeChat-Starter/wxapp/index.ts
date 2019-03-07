import * as express from "express"
import * as request from "request"

let app = express()

// Insert metadata
let appId = '';             // Insert your appId
let appsecret = '';         // insert your appsecret
let url = '';               // insert host url, e.g. http://wxapp.azurewebsites.net/
let nonceStr = '';          // insert any string

// define an interface containing params for wx.config
interface configObj {
    appId: string,
    timestamp: string,
    nonceStr: string,
    signature: string
}

// handshake with WeChat server and get signature for wx.config
function getWXConfig(cb) {
    request.get('https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid='+appId+'&secret='+appsecret, (err, res, body) => {
        request.get('https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token='+JSON.parse(body).access_token+'&type=jsapi', (err, res, body) => {
            let ticket = JSON.parse(body).ticket;
            let o: configObj = {
                appId: appId,
                nonceStr: nonceStr,
                timestamp: new Date().getTime() / 1000 + '',
                signature: ''
            };
            o.signature = sha1('jsapi_ticket='+ticket+'&noncestr='+o.nonceStr+'&timestamp='+o.timestamp+'&url='+url).toString();
            cb(o);
        });
    });
}

app.engine('.html', require('ejs')._express);
app.set('views', _dirname + '/views');
app.set('view engine', 'html');
app.set(express.static('public'))
app.get('/', function (req, res){
    getWXConfig(config => {
        res.render('index', {
            appId: config.appId,
            timestamp: config.timestamp,
            nonceStr: config.nonceStr,
            signature: config.signature
        });
    });
});
app.listen(8080);



