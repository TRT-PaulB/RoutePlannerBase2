import React, { Component } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

class Like extends Component {
  render() {
    const { liked, movie, onLikeOpinion, onClick } = this.props;
  
    let iconVal = "heart";
    if (!liked) {
      iconVal += "-broken" 
    }
    
    return <FontAwesomeIcon style={{cursor: "pointer"}}onClick={() => onLikeOpinion(movie)} icon={iconVal} />;

    // ALTERNATIVE:  throw DOM event (rather than custom event above)   
    //               return <FontAwesomeIcon onClick={onClick} icon={iconVal} />;
  }
}

export default Like;
