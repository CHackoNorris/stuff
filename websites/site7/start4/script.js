console.clear();
const scale = window.innerWidth > 700 ? 40 : 20;
let _w = Math.min(window.innerWidth, window.innerHeight) - 80;
_w = Math.min(_w, 1000);
_w = _w - (_w % scale);
let _h = _w;
document.querySelector('.container').style.width = _w + 'px';

let grid = [];
let paths = [];
const svg = document.querySelector('svg');
const svgGroup = document.querySelector('.paths');
const svgLeaves = document.querySelector('.leaves');
const xmlns = 'http://www.w3.org/2000/svg';
svg.setAttribute('viewBox', `0 0 ${_w} ${_w}`);


function reset () {
  _w = Math.min(window.innerWidth, window.innerHeight) - 80;
  _w = Math.min(_w, 1000);
  _w = _w - (_w % scale);
  _h = _w;
  document.querySelector('.container').style.width = _w + 'px';
  grid = [];
  paths = [];
  
  gsap.killTweensOf('*');
  
  safe = 4000;
  tempPath = [];
  
  svgGroup.innerHTML = '';
  svgLeaves.innerHTML = '';
  magic();
}
document.querySelector('button').addEventListener('click', () => {
  reset();
});
function setup () {
  let c = createCanvas(_w, _h);
  c.parent(document.querySelector('.container'));
  document.querySelector('main').remove();
  magic();
}

function magic () {
  for (let y = 0; y < _h; y+=scale) {
    let row = [];
    for (let x = 0; x < _w; x+=scale) {
      row.push({
        color: random(255),
        x: x,
        y: y,
        from: null,
        to: null
      });
    }
    grid.push(row);
  }
  
  startWalking(Math.floor(Math.random() * grid.length), Math.floor(Math.random() * grid[0].length));
  svgGroup.innerHTML = '';
  paths.forEach(p => {
    renderSVG(p);
  });
  gsap.set('.paths path', {
    strokeDashoffset: (i, path) => {
      return path.getTotalLength();
    },
    strokeDasharray: (i, path) => {
      return path.getTotalLength();
    }
  });
  document.querySelectorAll('.paths path').forEach(p => {
    p.lastImg = -1;
    p.length = p.getTotalLength();
    gsap.to(p, {
      strokeDashoffset: 0,
      onUpdate: function () {
        const offset = parseFloat(p.style.strokeDashoffset);
        if (p.lastImg !== -1 && p.lastImg - offset < 20) {
          return;
        }
        if (Math.random() > 0.93) {
          addOneLeaf(p.getPointAtLength(p.length - offset));
          p.lastImg = offset;
        }
        if (Math.random() > 0.992) {
          addOneFlower(p.getPointAtLength(p.length - offset));
          p.lastImg = offset;
        }
        if (Math.random() > 0.997) {
          addOneButterfly(p.getPointAtLength(p.length - offset));
          p.lastImg = offset;
        }
      },
      duration: (i, path) => {
        return (Math.random() * 0.6 + 0.4) * (path.getTotalLength() * 0.02)
      },
      delay: () => {
        return Math.random() * 4
      }
    });
  })
  
  clear();
  renderGrid();
}

let safe = 4000;
let tempPath = [];
function startWalking (row, col) {
  const cell = grid[row][col];
  safe--;
  if (safe < 0) {
    return;
  }
  // If the element is already moving to somewhere, return
  if (cell.stuck) {
    return;
  };
  // If four directions are not available, return from new place
  if (
    ((!grid[row - 1] || grid[row - 1][col].from !== null) &&
    (!grid[row + 1] || grid[row + 1][col].from !== null) &&
    (!grid[row][col + 1] || grid[row][col + 1].from !== null) &&
    (!grid[row][col - 1] || grid[row][col - 1].from !== null)) ||
    (cell.to !== null && cell.from !== null)
  ) {
    if (tempPath.length) {
      paths.push(tempPath);
    }
    tempPath = [];
    startWalking(Math.floor(Math.random() * grid.length), Math.floor(Math.random() * grid[0].length));
    cell.stuck = true;
    return;
  };
  
  // Give an angle to the start
  if (cell.from === null) {
    cell.from = random(['top', 'left', 'right', 'bottom']);
    tempPath.push(cell);
  }
  
  let hasWalked = false;
  // Find a direction (top/left/bottom/right);
  const to = random(['top', 'left', 'right', 'bottom'].filter(angle => angle !== cell.from));
  cell.to = to;
  // Check if top cell is available
  if (to === 'top') {
    if (grid[row - 1] && grid[row - 1][col].from === null && grid[row - 1][col].to === null) {
      grid[row - 1][col].from = 'bottom';
      tempPath.push(grid[row - 1][col]);
      startWalking(row - 1, col);
      hasWalked = true;
    }
  }
  // Check if bottom cell is available
  if (to === 'bottom') {
    if (grid[row + 1] && grid[row + 1][col].from === null && grid[row + 1][col].to === null) {
      grid[row + 1][col].from = 'top';
      tempPath.push(grid[row + 1][col]);
      startWalking(row + 1, col);
      hasWalked = true;
    }
  }
  // Check if right cell is available
  if (to === 'right') {
    if (grid[row][col + 1] && grid[row][col + 1].from === null && grid[row][col + 1].to === null) {
      grid[row][col + 1].from = 'left';
      tempPath.push(grid[row][col + 1]);
      startWalking(row, col + 1);
      hasWalked = true;
    }
  }
  // Check if left cell is available
  if (to === 'left') {
    if (grid[row][col - 1] && grid[row][col - 1].from === null && grid[row][col - 1].to === null) {
      grid[row][col - 1].from = 'right';
      tempPath.push(grid[row][col - 1]);
      startWalking(row, col - 1);
      hasWalked = true;
    }
  }
  if (!hasWalked) {
    cell.to = null;
    startWalking(row, col);
  }
}

function renderGrid () {
  fill(255);
  stroke(0);
  strokeWeight(1);
  grid.forEach(row => {
    row.forEach(cell => {
      square(cell.x, cell.y, scale);
    });
  })
}

function renderSVG (data) {
  const path = document.createElementNS(xmlns, 'path');
  let d = '';
  let cell = data[0];
  
  if (cell.from === 'left' && cell.to === 'top' ||
      cell.from === 'left' && cell.to === 'right' ||
      cell.from === 'left' && cell.to === 'bottom') {
    d += `M ${cell.x} ${cell.y + scale / 2} `;
  }
  
  if (cell.from === 'right' && cell.to === 'top' ||
      cell.from === 'right' && cell.to === 'left' ||
      cell.from === 'right' && cell.to === 'bottom') {
    d += `M ${cell.x + scale} ${cell.y + scale / 2} `;
  }
  
  if (cell.from === 'top' && cell.to === 'bottom' ||
      cell.from === 'top' && cell.to === 'right' ||
      cell.from === 'top' && cell.to === 'left') {
    d += `M ${cell.x + scale / 2} ${cell.y} `;
  }
  
  if (cell.from === 'bottom' && cell.to === 'top' ||
      cell.from === 'bottom' && cell.to === 'right' ||
      cell.from === 'bottom' && cell.to === 'left') {
    d += `M ${cell.x + scale / 2} ${cell.y + scale} `;
  }
  
  
  data.forEach((cell, i) => {
    if (cell.from === 'left' && cell.to === 'right') {
      d += `l ${scale} ${0} `;
    } else if (cell.from === 'right' && cell.to === 'left') {
      d += `l ${-scale} ${0} `;
    } else if (cell.from === 'top' && cell.to === 'bottom') {
      d += `l ${0} ${scale} `;
    } else if (cell.from === 'bottom' && cell.to === 'top') {
      d += `l ${0} ${-scale} `;
    } else if (cell.from === 'top' && cell.to === 'left') {
      d += `a ${scale/2} ${scale/2} 0 0 1 ${-scale/2} ${scale/2} `;
    } else if (cell.from === 'left' && cell.to === 'top') {
      d += `a ${scale/2} ${scale/2} 0 0 0 ${scale/2} ${-scale/2} `; 
    } else if (cell.from === 'top' && cell.to === 'right') {
      d += `a ${scale/2} ${scale/2} 0 0 0 ${scale/2} ${scale/2} `;
    } else if (cell.from === 'right' && cell.to === 'top') {
      d += `a ${scale/2} ${scale/2} 0 0 1 ${-scale/2} ${-scale/2} `; 
    } else if (cell.from === 'bottom' && cell.to === 'left') {
      d += `a ${scale/2} ${scale/2} 0 0 0 ${-scale/2} ${-scale/2} `;
    } else if (cell.from === 'left' && cell.to === 'bottom') {
      d += `a ${scale/2} ${scale/2} 0 0 1 ${scale/2} ${scale/2} `; 
    }else if (cell.from === 'bottom' && cell.to === 'right') {
      d += `a ${scale/2} ${scale/2} 0 0 1 ${scale/2} ${-scale/2} `;
    } else if (cell.from === 'right' && cell.to === 'bottom') {
      d += `a ${scale/2} ${scale/2} 0 0 0 ${-scale/2} ${scale/2} `;
    }
  });
  path.setAttribute('d', d);
  svgGroup.appendChild(path);
}

function addOneLeaf (coords) {
  // Add Leaf
  const group = document.querySelector(Math.random() > 0.5 ? '#leaf' : '#leaf2').cloneNode(true);
  let flip = Math.random() > 0.5;
  if (flip) {
    group.children[0].classList.add('flip');
  }

  gsap.set(group, {
    x: coords.x,
    y: coords.y,
    scale: 0,
    rotate: Math.random() * 180,
    transformOrigin: flip ? `100% 98%` : `2% 93%`
  });
  gsap.to(group, {
    scale: Math.random() * 0.4 + 0.05,
    duration: Math.random() * 1 + 0.5
  });
  svgLeaves.appendChild(group);
}

function addOneFlower (coords) {
  // Add Leaf
  const group = document.querySelector(Math.random() > 0.5 ? '#flower' : '#flower2').cloneNode(true);
  let flip = Math.random() > 0.5;
  if (flip) {
    group.children[0].classList.add('flip');
  }

  gsap.set(group, {
    x: coords.x,
    y: coords.y,
    scale: 0,
    rotate: Math.random() * 180,
    transformOrigin: 'top left'
  });
  gsap.to(group, {
    scale: Math.random() * 0.2 + 0.15,
    duration: Math.random() * 1 + 0.5
  });
  svgLeaves.appendChild(group);
}

function addOneButterfly (coords) {
  // Add Leaf
  const group = document.querySelector('#butterfly').cloneNode(true);
  let flip = Math.random() > 0.5;
  if (flip) {
    group.children[0].classList.add('flip');
  }

  gsap.set(group, {
    x: coords.x - (151.64 / 2),
    y: coords.y - (150.04 / 2),
    scale: 0,
    rotate: Math.random() * 360,
    transformOrigin: 'center'
  });
  gsap.to(group, {
    scale: Math.random() * 0.25 + 0.12,
    duration: Math.random() * 1 + 0.5
  });
  svgLeaves.appendChild(group);
}