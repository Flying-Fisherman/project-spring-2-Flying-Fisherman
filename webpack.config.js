const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');

const config = {
    mode: 'development',
   	// 주를 이루는 JavaScript 파일(엔트리 포인트)
   	entry: './src/index.js',
   	// 파일 출력 설정
   	output: {
   		path: `${__dirname}/dist`, // 출력 파일 디렉토리 이름
   		filename: 'main.js' // 출력 파일 이름
    },
    resolve: {
        alias: {
          "react-dom": "@hot-loader/react-dom",
        },
    },
    // 모듈 로드
    module: {
        rules:[
            {
                test: /\.js$/,                  // .js 인 파일만
                use: [{
                        loader: 'babel-loader', // babel-loader 로 불러옴
                        options: {
                            presets: ["@babel/preset-react"] // react로 설정하여 babel 에 활용
                        }
                }],
                exclude: /node_modules/,
            },
            {
                test: /\.html$/,                // html 파일만
                loader: "html-loader",          // html-loader 로 불러옴
                exclude: /node_modules/,
            }
        ]
    },
    // 개발 서버 설정
    devServer: {
        contentBase: `${__dirname}/dist`,    // 작업 폴더
        compress: true,                      // 압축
        host: 'localhost',                   // 주소
        port: 3000,                          // 포트 번호
        open: true,                          // 실행시 웹브라우저 자동 실행
        disableHostCheck: true,              // 호스트 체크 안함
        hot: true
    },
    // 플러그인 설정
    plugins: [
        new HtmlWebpackPlugin({ // html 플러그인 로드 (html 을 실행 폴더에 복사하여 사용하는 방식)
            template: `${__dirname}/public/index.html`,      // index.html 을 뼈대로 사용
            filename: `${__dirname}/dist/index.html`, // 실행 폴더인 dist 에 index.html 복사함
            inhect: true           // webpack 으로 합쳐진 js 를 자동 임포트함
        }),
        new webpack.HotModuleReplacementPlugin()        // HMR 플러그인 로드
    ]
}

module.exports = config;
