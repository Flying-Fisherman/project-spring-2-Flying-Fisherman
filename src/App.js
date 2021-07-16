import React from 'react';
import MainContent from './components/js/MainContent';
import ScheduleMaker from './components/js/ScheduleMaker';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Navbar from './components/js/Navbar';
import SignUp from './components/js/SignUp';
import LogIn from './components/js/LogInModal';

function App() {
  return (
  <>
      <Router>
            <Navbar/>
        <Switch>
            <Route path="/" exact render={ () => <MainContent/> } />
            <Route path="/SignUp" exact component={SignUp} />
            <Route path="/making" exact component={ScheduleMaker} />
        </Switch>
      </Router>
  </>
  );
}

export default App;
