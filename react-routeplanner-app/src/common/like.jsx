import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

// convert likeClassVersion.jsx into a stateless functional component
// 1) create stateless functional component: sfc
// 2) copy and paste content FROM after "render() {" of and before "}"
//                           TO between "const Like = (props) => {"    and "}" (removing return statement)
// 3) remember to supply props as an argument ('this' keyword cannot be used)
const Like = props => {
  const { liked, onLike, movie } = props;

  let iconVal = "heart";
  if (!liked) {
    iconVal += "-broken";
  }

  return (
    <FontAwesomeIcon
      style={{ cursor: "pointer" }}
      icon={iconVal}
      onClick={() => onLike(movie)}
    />
  );
};

export default Like;
