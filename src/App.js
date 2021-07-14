import React from 'react';
import MainContent from './components/js/MainContent';
import ScheduleMaker from './components/js/ScheduleMaker';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

function App() {
  return (
  <Router>
    <Switch>
        <Route path="/" exact render={ () => <MainContent/> } />
        <Route path="/making" exact component={ScheduleMaker} />
    </Switch>
  </Router>
  );
}

export default App;
