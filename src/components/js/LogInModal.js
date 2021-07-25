import React, { useState } from 'react';
import axios from 'axios'
import SignUp from './SignUp';
import { withRouter } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';


const useStyles = makeStyles((theme) => ({
  root: {
    '& .MuiTextField-root': {
      margin: theme.spacing(2),
      width: 1000,
    },
  },
}));

function LogInModal({ history }) {

    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const [error, setError] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const handleOpen = () => {
      setOpen(true);
    };

    const handleClose = () => {
      setOpen(false);
      setError(false);
      setErrorMessage("");
      setUserId("");
      setPassword("");
    };

    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");

    const userIdHandler = (e) => {
        e.preventDefault();
        setUserId(e.target.value);
    };

    const passwordHandler = (e) => {
        e.preventDefault();
        setPassword(e.target.value);
    };

    let body = {
        id: userId,
        password: password,
    };

    const empty = () => {
        setUserId("");
        setPassword("");
    }

    const logIn = async () => {
        axios.post('http://localhost:8080/session', body)
        .then((res) => {
          registerSuccessful(userId, res.data.accessToken)
        })
        .catch(() => {
          console.log("ERROR is happened");
          logInFail();
        });
    }

    const logInFail = () => {
        setError(true);
        setErrorMessage("LogIn is failed. Check your ID or Password");
    }

    const registerSuccessful = (userId, token) => {
        localStorage.setItem('token', token);
        localStorage.setItem('authenticatedUser', userId);
        localStorage.setItem('log', true);

        setTimeout(function() {
          handleClose();
        }, 200);

        setTimeout(function() {
          goBack();
        }, 200);
    }

    const goBack = () => {
        history.push('/');
    }

    const logOut = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('authenticatedUser');
        localStorage.removeItem('log');

        setTimeout(function() {
         goBack();
        }, 500);
    }

    const goSignUp = () => {
        history.push('/signUp');
    }

    return (
        <>
        { localStorage.getItem('log')

        ? <>
          <div className="md-2" style={{color:"white", fontSize:"16px"}}>
          Hello! { localStorage.getItem('authenticatedUser') }
          </div>
          <button type="button" onClick={logOut} className="btn btn-primary md-2"
          style={{ marginLeft:"20px" }}>
            Log Out
          </button>
          </>


        : <>
          <button type="button" onClick={handleOpen} className="btn btn-primary md-offset-6 md-2">
            Log In
          </button>
          <button type="button" onClick={goSignUp} className="btn btn-primary md-2"
          style={{ marginLeft:"20px" }}>
            Sign Up
          </button>
          </>
        }

        <Dialog open={open} onClose={handleClose}
         aria-labelledby="form-dialog-title">

          <DialogTitle id="form-dialog-title">
            Log In
          </DialogTitle>

          <DialogContent>

            <TextField
             autoFocus
             required
             margin="dense"
             label="Your ID"
             fullWidth
             value={userId}
             onChange={userIdHandler}
             required
             variant="outlined"
             error={error}
            />

            <br/>

            <TextField
             label="password"
             type="password"
             value={password}
             onChange={passwordHandler}
             required
             variant="outlined"
             error={error}
            />

            <div>
              {errorMessage}
            </div>

          </DialogContent>

          <DialogActions>
            <Button onClick={handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={logIn} color="primary">
              Log In
            </Button>
          </DialogActions>

        </Dialog>

        </>
    )
}

export default withRouter(LogInModal);
