//
// Created by vincent on 20-2-14.
//

#ifndef CPP_VTIME_H
#define CPP_VTIME_H

#include <chrono>
#include <time.h>
#include <string>

namespace vincent
{
    std::string FormatTimeNow(const char *format)
    {
        auto now = std::chrono::system_clock::now();
        std::time_t now_c = std::chrono::system_clock::to_time_t(now);
        std::tm *now_tm = std::localtime(&now_c);

        char buf[20];
        std::strftime(buf, sizeof(buf), format, now_tm);
        return std::string(buf);
    }

} // namespace vincent

#endif //CPP_VTIME_H
