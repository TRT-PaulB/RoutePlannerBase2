import React, { Component } from "react";
import _ from "lodash";

class TableBody extends Component {
  renderCell = (item, column) => {
    if (column.content) return column.content(item); // if it is a react component, return the movie arg for conent

    return _.get(item, column.name); // returns the full column name
  };

  createColumnKey = (item, column) => {
    return item._id + (column.name || column.key);
  };

  render() {
    const { data, columns } = this.props;

    return (
      <tbody>
        {data.map(item => (
          <tr key={item._id}>
            {columns.map(column => (
              <td key={this.createColumnKey(item, column)}>
                {this.renderCell(item, column)}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    );
  }
}
// WAS: {this.renderCell(item, column)} ******

export default TableBody;

//  lodash method needed because some properties are nested: ie cannot render item['genre.name']
// <td>{this.renderCell(column, item)}</td>
//<td>{_.get(item, column.name)}</td>
