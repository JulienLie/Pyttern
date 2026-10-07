#pragma once
#include <string>
#include <vector>
#include <memory>


struct Node : public std::enable_shared_from_this<Node> {
    std::string rule_name;
    std::string text;
    bool is_terminal = false;
    std::weak_ptr<Node> parent;
    std::vector<std::shared_ptr<Node>> children;

    int getChildCount() const {
        return children.size();
    }

    std::shared_ptr<Node> getChild(int i) const {
        if(i >= 0 && i < children.size()){
            return children[i];
        }
        return nullptr;
    }

    std::shared_ptr<Node> getChild(int i, std::string &rule_type) const {
        if(i < 0) return nullptr;
        if(rule_type.empty()) return this->getChild(i);

        int f = 0;
        for(const auto &child : this->children) {
            if(child->rule_name == rule_type) {
                if(f == i) return child;
                f++;
            }
        }
        return nullptr;
    }

    std::shared_ptr<Node> getParent() const {
        return parent.lock();
    }

    std::string getText() const {
        return text;
    }
};  