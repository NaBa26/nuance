import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import App from './index';
import Login from './login'; 

const Routes = () => {
  return (
    <Router>
      <Switch>
        <Route exact path="/home" component={App} />
        <Route exact path="/login" component={Login} />
      </Switch>
    </Router>
  );
};

export default Routes;
