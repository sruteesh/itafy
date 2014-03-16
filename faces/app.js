var express = require('express');
var http = require('http');
var path = require('path');
var favicon = require('static-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var routes = require('./routes');
var users = require('./routes/user');

var mongoose = require('mongoose');
var Schema = mongoose.Schema;
mongoose.connect("mongodb://127.0.0.1/itafy-benchmarks");
var db = mongoose.connection;
db.on("error", console.error.bind(console, "Connection error: "));



var twitterNameSchema = new Schema({
    name: String,
    screenName: String,
    description: String,
    language: String,
    location: {
        longitude: Number,
        latitude: Number
    },
    spain: Boolean,
    created_at: Date,
});
var TwitterName = mongoose.model("twitter_names", twitterNameSchema);


var app = express();


// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(favicon());
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
app.use(cookieParser());
app.use(require('less-middleware')({ src: path.join(__dirname, 'public') }));
app.use(express.static(path.join(__dirname, 'public')));
app.use(app.router);

app.get('/', function(req, res) {
    TwitterName
    .find({})
    .limit(10)
    .exec(function(err, faces) {
        res.render('faces', {faces: faces});
    });
});
app.get('/users', users.list);

app.post('/saveGender', function(req, res) {
    var gender = req.body.gender;

    TwitterName.save()
});

/// catch 404 and forwarding to error handler
app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

/// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
    app.use(function(err, req, res, next) {
        res.render('error', {
            message: err.message,
            error: err
        });
    });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
    res.render('error', {
        message: err.message,
        error: {}
    });
});

app.listen(3000);

module.exports = app;
