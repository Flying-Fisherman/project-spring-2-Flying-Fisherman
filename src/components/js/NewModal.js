import React from 'react';
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
      margin: theme.spacing(1),
      width: 200,
    },
  },
}));

function NewModal() {

    const classes = useStyles();
    const [open, setOpen] = React.useState(false);

    const handleOpen = () => {
      setOpen(true);
    };

    const handleClose = () => {
      setOpen(false);
    };

    return (
        <>

        <button type="button" onClick={handleOpen} className="btn btn-primary md-offset-6 md-2">
          Log In
        </button>

        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">

          <DialogTitle id="form-dialog-title">
            Log In
          </DialogTitle>

          <DialogContent>

            <TextField
             autoFocus
             required
             margin="dense"
             id="UserID"
             label="Your ID"
             fullWidth
            />

            <br/>

            <TextField
             id="UserPassword"
             label="password"
             type="password"
            />

          </DialogContent>

          <DialogActions>
            <Button onClick={handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={handleClose} color="primary">
              Subscribe
            </Button>
          </DialogActions>

        </Dialog>

        </>
    )
}

export default NewModal;
