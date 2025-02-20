let pyttern_fsm_cy = null;
let pyttern_tree_cy = null;
let code_tree_cy = null;


let current_fsm = 0
let current_code = 0


let fsm_nodes = []
let step = 0
let max_step = 0

function generate_cytoscape(){
    return cytoscape({
        style: [
            {
                selector: 'node',
                style: {
                    'background-color': '#BBB',
                    'label': 'data(label)'
                }
            },
            {
                selector: 'node[symbol]',
                style: {
                    'label': 'data(symbol)',
                    'text-valign': 'center',
                }
            }
            ,{
                selector: 'node.merged',
                style: {
                    'text-margin-y': '-10px', // adjusts the label positioning slightly up
                    'overlay-opacity': 0, // ensures no extra selection box around the node
                    'text-wrap': 'wrap', // to make sure it wraps if label is large
                    'text-rotation': 'none',
                    'text-valign': 'center',
                    'label': function(ele) { return ele.data("label") + "\n\n+"; }
                }
            },
            {
                selector: 'edge',
                style: {
                    'width': 3,
                    'line-color': '#ccc',
                    'target-arrow-color': '#ccc',
                    'target-arrow-shape': 'triangle',
                    'curve-style': 'bezier',
                }
            },
            {
                selector: 'edge[label]',
                style: {
                    label: 'data(label)',
                }
            }
        ]
    })
}

function recursive_generation(node, cy) {
    let new_elem = cy.add({
            group: 'nodes',
            data: {label: node.name, id:node.id, merged: false},
            //grabbable: false
        })
    new_elem.on('click', function(evt){
        isMerged = new_elem.data("merged")
        if(!isMerged){
            new_elem.data("merged", true)
            new_elem.addClass("merged"); // Add class to display "+"
            successors = new_elem.successors()
            successors.style({ 'display': 'none' })
            successors.data("merged", false)
        }
        else{
            new_elem.data("merged", false)
            new_elem.removeClass("merged"); // Remove class to hide "+"
            new_elem.successors().style({ 'display': 'element' })
        }
        cy.layout({name: 'dagre', padding: 5, fit: false, nodeDimensionsIncludeLabels: true}).run()
        console.log(new_elem.data("merged"))
    })
    if("symbol" in node) new_elem.data("symbol", node.symbol)
    for(let child of node.children) {
        let child_elem = recursive_generation(child, cy)
        cy.add({
            group: 'edges',
            data: { source: new_elem.id(), target: child_elem.id()}
        })
    }
    return new_elem
}

function change_pyttern_graph(){
    let value = $("#pyttern-graph-select").val()
    if(value === "fsm"){
        pyttern_tree_cy.unmount()
        pyttern_fsm_cy.mount($("#pyttern-cy"))
        pyttern_fsm_cy.layout({name: 'grid', padding: 5, avoidOverlapPadding:200}).run()

        $("#follow_pyttern").attr("disabled", false)
    }
    else{
        pyttern_fsm_cy.unmount()
        pyttern_tree_cy.mount($("#pyttern-cy"))
        pyttern_tree_cy.layout({name: 'dagre', padding: 5, fit: true, nodeDimensionsIncludeLabels: true}).run()

        $("#follow_pyttern").attr("disabled", true)
    }

}

function generate_pyttern_graph(fsm, tree){
    generate_pyttern_tree(tree)
    generate_pyttern_fsm(fsm)
    change_pyttern_graph()
}

function generate_pyttern_tree(json){
    if (pyttern_tree_cy == null) {
        pyttern_tree_cy = generate_cytoscape()
        console.log(pyttern_tree_cy)
    }

    recursive_generation(json, pyttern_tree_cy)
    pyttern_tree_cy.layout({name: 'dagre', padding: 5, fit: true, nodeDimensionsIncludeLabels: true}).run()
    console.log("Graph generated!")
}


function generate_pyttern_fsm(json){
    if (pyttern_fsm_cy == null) {
        pyttern_fsm_cy = generate_cytoscape()
    }

    let transitions = []
    for(let elem of json){
        elem = JSON.parse(elem)
        let node = pyttern_fsm_cy.add({
            group: 'nodes',
            data: { id: elem.id, label: elem.id },
            //grabbable: false
        })
        node.on('click', function(evt){
            console.log(elem)
        })
        fsm_nodes.push(node)
        for(let transition of elem.transitions){
            transitions.push({label:`${transition.func}`, source: elem.id, target: transition.next, mov: transition.mov})
        }
    }
    for(let transition of transitions){
        let elem = pyttern_fsm_cy.add({
            group: 'edges',
            data: { source: transition.source, target: transition.target, label: transition.label}
        })
        elem.on('click', function(evt){
            console.log(transition)
            pop(transition)(evt)
        })
    }
    console.log("Graph generated!")
}

function select_pyttern_graph(value){
    console.log(value)
}

function generate_code_graph(pyttern_json){
    if (code_tree_cy == null) {
        code_tree_cy = generate_cytoscape()
        code_tree_cy.mount($("#code-cy"))
    }

    recursive_generation(pyttern_json, code_tree_cy)
    code_tree_cy.layout({name: 'dagre', padding: 5, fit: true, nodeDimensionsIncludeLabels: true}).run()
    console.log("Graph generated!")
}

function reset_code_graph(){
    code_tree_cy.fit()
}

function reset_pyttern_graph(){
    pyttern_fsm_cy.fit()
    pyttern_tree_cy.fit()
}

function set_current_step(new_step){
    new_step = Math.max(0, Math.min(new_step, max_step))
    step = new_step
    return $.ajax({
        type: 'POST',
        url: '/api/step',
        contentType: 'application/json',
        data: JSON.stringify({step: new_step}),
        success: function(data){
            data = JSON.parse(data)
            current_fsm = data.state[0]
            current_code = data.state[1]
            let current_matchings = data.current_matchings
            let previousMatchings = data.previous_matchings
            update_current_step(current_matchings, previousMatchings)
            //update_pyttern_code(data['positions']['fsm']['start'], data['positions']['fsm']['end'])
            //update_code(data['positions']['ast']['start'], data['positions']['ast']['end'])

            let variables = data['variables']
            console.log(variables)

            let messages = data['messages']
            show_messages(messages)
        }
    })
}

function show_messages(messages){
    if(messages.length === 0){
        return
    }
    const toastLiveExample = document.getElementById('liveToast')

    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)

    let messages_area = $("#toast-message")
    messages_area.empty()
    for(let message of messages){
        messages_area.html(`<p>${message}</p>`)
    }
    toastBootstrap.show()
}

function update_pyttern_code(start, end){
    if(start === null || end === null) return

    const code_area = $('#pyttern')[0]
    const code = code_area.innerText
    const lines = code.split("\n")
    const end_line = lines[end[0]]
    let new_end_line = ""
    for (let i = 0; i < end_line.length; i++){
        new_end_line += end_line[i]
        if (i+1 === end[1]){
            new_end_line += "</span>"
        }
    }
    lines[end[0]] = new_end_line

    const start_line = lines[start[0]]
    let new_start_line = ""
    for (let i = 0; i < start_line.length; i++){
        if (i === start[1]){
            new_start_line += "<span style='background-color: yellow'>"
        }
        new_start_line += start_line[i]
    }
    lines[start[0]] = new_start_line
    code_area.innerHTML = lines.join("\n")
}

function update_code(start, end){
    const code_area = $('#code')
    const code = code_area.val()
}

function enable_buttons(){
    if(step === 0){
        $('#first').attr('disabled', true)
        $('#prev').attr('disabled', true)
    }
    else{
        $('#first').attr('disabled', false)
        $('#prev').attr('disabled', false)
    }

    if(step >= max_step){
        $('#next').attr('disabled', true)
        $('#last').attr('disabled', true)
    }
    else{
        $('#next').attr('disabled', false)
        $('#last').attr('disabled', false)
    }
}

function update_pyttern_nodes(current_matches, previous_matches){
    pyttern_fsm_cy.nodes().style({ 'background-color': '#BBB' })

    for (let node of previous_matches){
        let previous_pyttern_node = pyttern_fsm_cy.$id(node)
        previous_pyttern_node.style({ 'background-color': 'red' })
    }

    for (let node of current_matches){
        let current_pyttern_node = pyttern_fsm_cy.$id(node)
        current_pyttern_node.style({ 'background-color': 'blue' })
    }

    let current_pyttern_node = pyttern_fsm_cy.$id(current_fsm)
    current_pyttern_node.style({ 'background-color': 'green' })
    if($("#follow_pyttern").is(":checked")) {
        pyttern_fsm_cy.animation({
            center: {eles: current_pyttern_node},
            duration: 200
        }).play()
    }
}

function update_code_nodes(current_matches, previous_matches){
    code_tree_cy.nodes().style({ 'background-color': '#BBB' })

    for (let node of previous_matches){
        let previous_pyttern_node = code_tree_cy.$id(node)
        previous_pyttern_node.style({ 'background-color': 'red' })
    }

    for (let node of current_matches){
        let current_pyttern_node = code_tree_cy.$id(node)
        current_pyttern_node.style({ 'background-color': 'blue' })
    }

    let current_code_node = code_tree_cy.$id(current_code)
    current_code_node.style({ 'background-color': 'green' })
    if($("#follow_code").is(":checked")) {
        code_tree_cy.animation({
            center: {eles: current_code_node},
            duration: 200
        }).play()
    }
}

function update_current_step(current_matchings, previous_matchings){
    enable_buttons()

    console.log("Setting current fsm to " + step)

    const current_pyttern_matches = []
    const current_code_matches = []
    for (let state of current_matchings){
        current_pyttern_matches.push(state[0])
        current_code_matches.push(state[1])
    }

    const previous_pyttern_matches = []
    const previous_code_matches = []
    for (let state of previous_matchings){
        previous_pyttern_matches.push(state[0])
        previous_code_matches.push(state[1])
    }

    update_pyttern_nodes(current_pyttern_matches, previous_pyttern_matches)
    update_code_nodes(current_code_matches, previous_code_matches)

    $("#step").attr("value", step)
    $("#range").attr("value", step)
}

function add_current_step(){
    step += 1
    set_current_step(step)
}

function remove_current_step(){
    step -= 1
    set_current_step(step)
}

function goto_first_step(){
    set_current_step(0)
}

function goto_last_step(){
    set_current_step(max_step)
}

function set_matching_states(matching_states){
    let matching_states_area = $("#matches")
    matching_states_area.empty()
    for(let state of matching_states){
        matching_states_area.append(`<option value=${state}></option>`)
    }
}

function start_simulator(){
    return $.ajax({
        type: 'POST',
        url: '/api/start',
        contentType: 'application/json',
        success: function(data){
            data = JSON.parse(data)
            max_step = data["n_steps"]
            current_fsm = data['state'][0]
            current_code = data['state'][1]
            matching_state = data['match_states']
            set_matching_states(matching_state)
            $("#step").attr("max", max_step)
            $("#range").attr("max", max_step)
            set_current_step(0)
        }
    })
}

function pop(transition) {
    function _pop(evt) {
        // Get the position where the user clicked
        const cyContainer = document.getElementById('pyttern-cy');
        const rect = cyContainer.getBoundingClientRect();
        const position = evt.renderedPosition || evt.position;

        // Remove any existing popover
        const old = $('.popover');
        old.popover('dispose');
        old.remove();

        // Create a popover HTML element with dynamic content
        const popoverHtml = `
        <div id="popover-content" class="popover">
            <strong>Transition:</strong> ${transition.label}<br>
            <strong>Source:</strong> ${transition.source}<br>
            <strong>Target:</strong> ${transition.target}<br>
            <strong>Movement:</strong> ${transition.mov}
        </div>
    `;

        // Create a temporary div to attach the popover to
        const popoverId = `popover-${Math.random().toString(36).substr(2, 9)}`;
        $('body').append(`<div id="${popoverId}" style="position: absolute;"></div>`);

        // Position the div at the click location
        let popover = $(`#${popoverId}`)
        popover.css({
            top: (position.y + rect.top) + 'px',
            left: (position.x + rect.left) + 'px'
        });

        // Initialize the Bootstrap popover
        popover.popover({
            trigger: 'manual',
            html: true,
            content: popoverHtml,
            placement: 'top'
        }).popover('show');

        // Optionally hide the popover after a few seconds or when clicking elsewhere
        setTimeout(() => {
            popover.popover('dispose');
            popover.remove(); // Clean up the DOM element after hiding
        }, 3000); // Hide after 3 seconds
    }
    return _pop
}

function download_pyttern_graph(){
    let value = $("#pyttern-graph-select").val()
    let cy = null
    if(value === "fsm"){
        cy = pyttern_fsm_cy
    }
    else{
        cy = pyttern_tree_cy
    }
    let png = cy.png({full: true})
    let a = document.createElement('a');
    a.href = png
    a.download = 'pyttern_graph.png'
    a.click()
}

function download_code_graph(){
    let png = code_tree_cy.png({full: true})
    let a = document.createElement('a');
    a.href = png
    a.download = 'code_graph.png'
    a.click()
}